import React, { Fragment, useState,useCallback,useEffect } from "react";
import { Unity, useUnityContext } from "react-unity-webgl";
import UserAPI from "../../api/UserAPI";

function World() {
  const [progress, setProgress] = useState();


  const { unityProvider, addEventListener, removeEventListener,sendMessage } = useUnityContext({
    loaderUrl: "Build/build.loader.js",
    dataUrl: "Build/build.data",
    frameworkUrl: "Build/build.framework.js",
    codeUrl: "Build/build.wasm",
  });

  function handleClickSpawnEnemies() {
    console.log("asdfsadf")
    sendMessage("LoadManager", "LoadData",progress);
  }


  const loadData = useCallback(async () => {

    console.log("start")

    const response=await UserAPI.mypage()
    setProgress(response.data.body.missionProgress);

  }, []);

  const saveProgress = useCallback(async (progress) => {
    console.log("saveProgress",progress)
    const body={
      "progress":progress
    }
    const response=await UserAPI.progress(body)
    setProgress(response.data.body.progress);
    // handleClickSpawnEnemies()

  }, []);


  useEffect(() => {
    addEventListener("GameStart", loadData);
    addEventListener("SetProgress", saveProgress);
    return () => {
      removeEventListener("GameStart", loadData);
      removeEventListener("SetProgress", saveProgress);
    };
  }, [addEventListener, removeEventListener, loadData,saveProgress]);
  
  return (

      <Fragment>
        <button onClick={handleClickSpawnEnemies}>버튼</button>
        {progress}
        {handleClickSpawnEnemies()}
        <Unity style={{width:'100%', height:'100%', justifySelf:'center',alignSelf:'center',}} unityProvider={unityProvider}/>
      
      </Fragment>

    

  );

  
}

export default World;