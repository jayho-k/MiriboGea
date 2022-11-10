// import UserAPI from "../api/UserAPI";
// import { useState, useEffect } from "react";
// import { useSelector } from "react-redux";
// import { useNavigate } from "react-router-dom";
// import { selectUser } from "../app/redux/userSlice";
import NavBar from "./Navbar";
import style from "./css/Mainpage.module.css";
import main from "../asset/img/main.png";
import intro from "../asset/img/intro.png";

function MainPage(){
  // const user = useSelector(selectUser);
  return (
    <div className={style.mainBackground}>
      <NavBar/>
      <section className={style.section1}>
        <img className={style.dogimg} src={main} alt=""/>
        <p className={style.startText}>사진을 눌러서 시작해보세요.</p>
        <div className={style.down}>
          <img style={{width:'184px'}} src={intro} alt=""/>
        </div>
      </section>
    </div>
  )
}

export default MainPage;