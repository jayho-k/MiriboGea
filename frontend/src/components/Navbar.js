import Logo from "../asset/img/logo.png";
import styles from "./css/Navbar.module.css";
import { useSelector } from "react-redux";
import { useNavigate, useLocation } from "react-router-dom";
import { selectToken } from "../app/redux/userSlice";
import React, { useState } from 'react';

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
        <div styles={{ position: "absolute", left: "32px", top: "88px" }}>
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
    </div>
  );
}

function NavBar({props}){
  const [openModal, setOpenModal] = useState(false);
  const showModal = () => {
    setOpenModal(true);
    props.setLogoutModalOpen(true);
  };
  const navigate = useNavigate();
  const location = useLocation();
  const token = useSelector(selectToken);

  const goToLogin = () => {
    navigate("/auth");
  };
  const goToMain = () => {
    navigate("/");
  };
  return(
    <div className={styles.nav}>
      {/* <img onClick={goToMain} className={styles.logo} src={Logo} alt=""/> */}
      { location.pathname === "/" && token === "" ? 
        <div className={styles.login} onClick={goToLogin}>
          <p className={styles.text}>Login</p>
        </div>
        : null}
        { location.pathname === "/" && token !== "" ?
        <div className={styles.login} onClick={showModal}>
          <p className={styles.text}>Logout</p>
        </div>:null}
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
export default NavBar;