import React, { Fragment, useState, useCallback, useEffect } from "react";
import { Unity, useUnityContext } from "react-unity-webgl";
import UserAPI from "../../api/UserAPI";
import BoardAPI from "../../api/BoardAPI";

function World() {
  const [progress, setProgress] = useState();

  const { unityProvider, addEventListener, removeEventListener, sendMessage } =
    useUnityContext({

      loaderUrl: "./Build/Build.loader.js",
      dataUrl: "./Build/Build.data",
      frameworkUrl: "./Build/Build.framework.js",
      codeUrl: "./Build/Build.wasm",

    });

  function handleClickSpawnEnemies() {
    console.log("asdfsadf");
    sendMessage("QuestManager", "setProgress", progress);
  }

  const loadData = useCallback(async () => {
    console.log("start");
    const response = await UserAPI.mypage();
    setProgress(response.data.body.missionProgress);

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

  useEffect(() => {
    addEventListener("GameStart", loadData);
    addEventListener("MissionClear", saveProgress);

    return () => {
      removeEventListener("GameStart", loadData);
      removeEventListener("MissionClear", saveProgress);
    };

  }, [addEventListener, removeEventListener, loadData, saveProgress]);
  return (
    <Fragment>
      <Unity
        style={{
          width: "100%",
          height: "100%",
          justifySelf: "center",
          alignSelf: "center",
        }}
        unityProvider={unityProvider}
      />
      {handleClickSpawnEnemies()}
    </Fragment>
  );
}

export default World;
