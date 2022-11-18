import React from "react";
import style from "../css/Auth.module.css";
import logindog from "../../asset/img/logindog.png";
import kakaologin from "../../asset/img/kakaologin.png";
import NavBar from "../Navbar";
import Menu from "../Menu";

function login() {
  window.location.replace(
    `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_KAKAO_CLIENT_ID}&redirect_uri=${process.env.REACT_APP_KAKAO_LOGIN_REDIRECT_URL}&response_type=code`
  );
}

function Auth() {
  return (
    <div className={style.mainBackground}>
      <NavBar/>
      <Menu/>
      <img className={style.logindog} src={logindog} alt=""/>
      <img className={style.kakaologin} onClick={login} src={kakaologin} alt=""/>
    </div>
  );
}

export default Auth;
