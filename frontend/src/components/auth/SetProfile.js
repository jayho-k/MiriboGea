import { React, useState } from "react";
import styles from "./SetProfile.module.css";

import UserAPI from "../../api/UserAPI";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { selectUser } from "../../app/redux/userSlice";

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
    if (e.target.value !== "") {
      UserAPI.validCheck(e.target.value).then((response) => {
        if (response.data.body) {
          setCheckMsg("사용 가능한 닉네임 입니다.");
          setValidFlag(true);
        } else {
          setCheckMsg("이미 사용 중인 닉네임 입니다.");
          setValidFlag(false);
        }
      });
    } else {
      setValidFlag(false);
      setCheckMsg("");
    }
  };
  const join = () => {
    console.log("asdf")
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
    <div>
      <div className={styles.title}>
        <span>프로필 설정</span>
      </div>
      <div className={styles.photoBox}>
        <div className={styles.photo}>
          <img
            style={{ width: "100px", height: "100px", borderRadius: "100px" }}
            src={fileImage}
            alt="sample"
          />
        </div>
      </div>

      <div className={styles.inputBox}>
        <p>
          닉네임<span style={{ color: "red" }}>*</span>
        </p>
        <input
          className={styles.inputTag}
          name="nickname"
          type="text"
          placeholder="닉네임을 입력해주세요."
          value={nickname}
          onChange={handleChange}
        />
      </div>

      <div className={styles.checkMsg}>
        <span>{checkMsg}</span>
      </div>

      <div >
        <button
          
          onClick={join}
          disabled={false}
        >
          join
        </button>
      </div>

    </div>
  );
}

export default SetProfile;
