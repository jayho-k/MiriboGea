import { React, useState } from "react";
import styles from "./SetProfile.module.css";
import profile from "../../asset/img/profile.png"
import ok from "../../asset/img/OK.png"
import UserAPI from "../../api/UserAPI";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { selectUser } from "../../app/redux/userSlice";
import NavBar from "../Navbar";
import Menu from "../Menu";

function Modal({ onClose }) {
  function handleClose() {
    onClose?.();
  }
  const navigate = useNavigate();
  const goToMain = () => {
    navigate('/')
  }
  const user = useSelector(selectUser);

  return (
    <div>
      {user === "" ?
      <div className={styles.Modal} onClick={handleClose}>
        <div className={styles.ModalBody} onClick={(e) => e.stopPropagation()}>
          <div>
            <svg
              className={styles.modalCloseBtn}
              onClick={handleClose}
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <rect width="24" height="24" rx="12" fill="#E5E5E5" />
              <path
                d="M12 10.8891L15.8891 7L17 8.11094L13.1109 12L17 15.8891L15.8891 17L12 13.1109L8.11094 17L7 15.8891L10.8891 12L7 8.11094L8.11094 7L12 10.8891Z"
                fill="#4F4F4F"
              />
            </svg>
          </div>
          <div style={{ position: "absolute", left: "32px", top: "72px" }}>
            <p className={styles.ModalText}>다른 닉네임을 사용해주세요!</p>
          </div>
        </div>
      </div> : 
      <div className={styles.Modal} onClick={handleClose}>
      <div className={styles.LOModalBody} onClick={(e) => e.stopPropagation()}>
        <div>
          <svg
            className={styles.modalCloseBtn}
            onClick={handleClose}
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <rect width="24" height="24" rx="12" fill="#E5E5E5" />
            <path
              d="M12 10.8891L15.8891 7L17 8.11094L13.1109 12L17 15.8891L15.8891 17L12 13.1109L8.11094 17L7 15.8891L10.8891 12L7 8.11094L8.11094 7L12 10.8891Z"
              fill="#4F4F4F"
            />
          </svg>
        </div>
        <div style={{ position: "absolute", left: "32px", top: "88px" }}>
          <p className={styles.ModalText}>
           어서오세요! 반갑습니다!
          </p>
        </div>
        <div className={styles.buttonBox}>
          <button className={styles.NextButton} onClick={goToMain}>
            Yes
          </button>
        </div>
      </div>
    </div>}
    </div>
    
  );
}

function SetProfile() {

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
  const [openModal, setOpenModal] = useState(false);
  const showModal = () => {
    setOpenModal(true);
  };

  return (
    <div className={styles.setProfileBox}>
      <NavBar/>
      <Menu/>
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
            maxLength={8}
            value={nickname}
            onChange={handleChange}
          />
          <div>
            <img className={styles.ok} onClick={()=>{join();showModal();}} disabled={false} src={ok} alt=""/>
          </div>
        </div>

      {/* <div className={styles.checkMsg}>
        <span>{checkMsg}</span>
      </div> */}
      </div>
      {openModal && (
          <Modal
            open={openModal}
            onClose={() => {
              setOpenModal(false);
            }}
          />
        )}
    </div>
  );
}

export default SetProfile;
