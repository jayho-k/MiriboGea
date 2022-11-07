import styles from "./css/Menu.module.css";
import React, { useState } from 'react';
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { selectToken } from "../app/redux/userSlice";
import hamburger from "../asset/img/hamburger.png"
import home from "../asset/img/home.png"
import logout from "../asset/img/logout.png"
import board from "../asset/img/board.png"

function Modal({ onClose }) {
  function handleClose() {
    onClose?.();
  }
  const token = useSelector(selectToken);

  const logOut = () => {
    localStorage.removeItem('persist:root');
    localStorage.removeItem('token');
    window.location.replace('/');
  }
  return (
    <div>
      {token === "" ?
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
            <p className={styles.ModalText}>현재 로그인이 되어있지않습니다.</p>
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
           로그아웃 하시겠습니까?
          </p>
        </div>
        <div className={styles.buttonBox}>
          <button className={styles.NextButton} onClick={logOut}>
            Yes
          </button>
        </div>
      </div>
    </div>}
    </div>
    
  );
}

function Menu(){
  const [openModal, setOpenModal] = useState(false);
  const showModal = () => {
    setOpenModal(true);
  };

  const [state,setState] = useState(false);
  const onClick = () => {
    setState(!state)
  }
  const navigate = useNavigate();
  const goToMain = () => {
    navigate("/");
  };
  return(
  <div className={styles.menu}>
    <img onClick={goToMain} className={state ? styles.show : styles.hidden} src={home} alt=""/>
    <img className={state ? styles.show : styles.hidden} src={board} alt=""/>
    <img onClick={showModal} className={state ? styles.show : styles.hidden} src={logout} alt=""/>
    <img onClick={onClick} className={styles.bar} src={hamburger} alt=""/>
    {openModal && (
          <Modal
            open={openModal}
            onClose={() => {
              setOpenModal(false);
            }}
          />
        )}
  </div>
  )
}
export default Menu;