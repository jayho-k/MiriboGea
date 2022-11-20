// import UserAPI from "../api/UserAPI";
// import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { selectUser } from "../app/redux/userSlice";
import NavBar from "./Navbar";
import style from "./css/Mainpage.module.css";
import main from "../asset/img/main.png";
import intro from "../asset/img/intro.png";
import Menu from "./Menu";

import React, { Fragment, useState, useCallback, useEffect } from "react";
import { Unity, useUnityContext } from "react-unity-webgl";
import UserAPI from "../api/UserAPI";


function MainPage(){

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


  const navigate = useNavigate();
  const user = useSelector(selectUser);
  const [unityState,setUnityState]=useState({display: 'none'})
  const [mainStyle,setMainStyle]=useState({display: 'block'})
  const goToUnity = () => {
    // navigate("/Save_The_Dogs");
    setUnityState({display: 'block'})
    setMainStyle({display: 'none'})
  };
  const [logoutModalOpen,setLogoutModalOpen] = useState(false);
  return (
      <div className={style.mainBackground} >
        <div style={mainStyle}>
          <NavBar setLogoutModalOpen={setLogoutModalOpen}/>
            <section className={style.section1}>
            { logoutModalOpen ? 
            <div className={style.down}>
              <img onClick={goToUnity} className={style.dogimg} src={main} alt=""/>
              <p className={style.startText}>사진을 눌러서 시작해보세요.</p>
            </div>
            :
            <div className={style.downX}>
              <img onClick={goToUnity} className={style.dogimg} src={main} alt=""/>
              <p className={style.startText}>사진을 눌러서 시작해보세요.</p>
            </div>
            }
            </section>
          <Menu style={{postion:"fixed", bottom:"30px",left:"30px"}}/>
        </div>
        

        <div className={style.unity} style={unityState}>
        <Menu style={{postion:"fixed", bottom:"30px",left:"30px"}}/>
        <Unity
          style={{
            width: "100vw",
            height: "100vh",
            justifySelf: "center",
            alignSelf: "center",
          }}
          unityProvider={unityProvider}/>
          {handleClickSpawnEnemies()}
        </div>
      </div>
  )
}

export default MainPage;