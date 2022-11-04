import { React, useState } from "react";
import styles from "./SetProfile.module.css";
import profile from "../../asset/img/profile.png"
import ok from "../../asset/img/OK.png"
import UserAPI from "../../api/UserAPI";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { selectUser } from "../../app/redux/userSlice";
import NavBar from "../Navbar";

function SetProfile() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const user = useSelector(selectUser);
  const fileImage = user.profileURL;
  console.log(user)
  const [nickname, setNickname] = useState("");
  const [validFlag, setValidFlag] = useState(false);
  const [checkMsg, setCheckMsg] = useState("");

  const handleChange = (e) => {
    setNickname(e.target.value);
    // if (e.target.value !== "") {
    //   UserAPI.validCheck(e.target.value).then((response) => {
    //     if (response.data.body) {
    //       setCheckMsg("사용 가능한 닉네임 입니다.");
    //       setValidFlag(true);
    //     } else {
    //       setCheckMsg("이미 사용 중인 닉네임 입니다.");
    //       setValidFlag(false);
    //     }
    //   });
    // } else {
    //   setValidFlag(false);
    //   setCheckMsg("");
    // }
  };
  const join = () => {
    const body = {
      email: user.email,
      nickname: nickname,
      profileURL: user.profileURL,

    };
    UserAPI.join(body).then((response) => {
      console.log("response", response);
      
    });
    
  };

  return (
    <div className={styles.setProfileBox}>
      <NavBar/>
      <div className={styles.box}>
        <img className={styles.title} src={profile} alt=""/>
        <div className={styles.photoBox}>
          <div className={styles.photo}>
            <img
              style={{ width: "250px", height: "250px", borderRadius: "250px" }}
              src={fileImage}
              alt="sample"
            />
          </div>
        </div>
        <div className={styles.inputBox}>
          <input
            className={styles.inputTag}
            name="nickname"
            type="text"
            placeholder="닉네임을 입력해주세요."
            value={nickname}
            onChange={handleChange}
          />
          <div>
            <img className={styles.ok} onClick={join} disabled={false} src={ok} alt=""/>
          </div>
        </div>

      {/* <div className={styles.checkMsg}>
        <span>{checkMsg}</span>
      </div> */}
      </div>
    </div>
  );
}

export default SetProfile;
