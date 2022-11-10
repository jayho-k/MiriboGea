import React, { Fragment, useState, useCallback, useEffect } from "react";
import { Unity, useUnityContext } from "react-unity-webgl";
import UserAPI from "../../api/UserAPI";
import BoardAPI from "../../api/BoardAPI";

function World() {
  const [progress, setProgress] = useState();

  const { unityProvider, addEventListener, removeEventListener, sendMessage } =
    useUnityContext({
      loaderUrl: "Build/build.loader.js",
      dataUrl: "Build/build.data",
      frameworkUrl: "Build/build.framework.js",
      codeUrl: "Build/build.wasm",
    });

  function handleClickSpawnEnemies() {
    console.log("asdfsadf");
    sendMessage("LoadManager", "LoadData", progress);
  }

  const loadData = useCallback(async () => {
    console.log("start");

    const response = await UserAPI.mypage();
    setProgress(response.data.body.missionProgress);
    // handleClickSpawnEnemies()
  }, []);

  const saveProgress = useCallback(async (progress) => {
    console.log("saveProgress", progress);
    const body = {
      progress: progress,
    };
    const response = await UserAPI.progress(body);
    setProgress(response.data.body.progress);
    // handleClickSpawnEnemies()
  }, []);

  const story3ArticleCheck = useCallback(async () => {
    console.log("story3ArticleCheck");
    const response = await BoardAPI.story3check();
    console.log("응답 : ", response);
    sendMessage("PlayerFPS3", "Checked");
    // handleClickSpawnEnemies()
  }, []);

  useEffect(() => {
    addEventListener("GameStart", loadData);
    addEventListener("SetProgress", saveProgress);
    addEventListener("Story3ArticleCheck", story3ArticleCheck);

    return () => {
      removeEventListener("GameStart", loadData);
      removeEventListener("SetProgress", saveProgress);
      removeEventListener("Story3ArticleCheck", story3ArticleCheck);
    };
  }, [
    addEventListener,
    removeEventListener,
    loadData,
    saveProgress,
    story3ArticleCheck,
  ]);
  return (
    <Fragment>
      <button onClick={() => sendMessage("PlayerFPS3", "Checked")}>버튼</button>
      {progress}
      <Unity
        style={{
          width: "100%",
          height: "100%",
          justifySelf: "center",
          alignSelf: "center",
        }}
        unityProvider={unityProvider}
      />
    </Fragment>
  );
}

export default World;
