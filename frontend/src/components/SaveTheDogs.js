import React from "react";
import { Unity, useUnityContext } from "react-unity-webgl";
import Menu from "./Menu";

function SaveTheDogs (){
  const { unityProvider } = useUnityContext({
    loaderUrl: "Build/Build.loader.js",
    dataUrl: "Build/Build.data",
    frameworkUrl: "Build/Build.framework.js",
    codeUrl: "Build/Build.wasm",
  });

  return (
  <div style={{width:'100vw',height:'100vh'}}>
    <Menu/>
    <Unity unityProvider={unityProvider} />
  </div>
  )
}
export default SaveTheDogs;