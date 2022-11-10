import Logo from "../asset/img/logo.png";
import style from "./css/Navbar.module.css";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate, useLocation } from "react-router-dom";
import { selectToken } from "../app/redux/userSlice";

function NavBar(){
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
    <div className={style.nav}>
      {/* <img onClick={goToMain} className={style.logo} src={Logo} alt=""/> */}
      { location.pathname === "/" && token === "" ? 
        <div >
        <div className={style.login} onClick={goToLogin}>
          <p className={style.text}>Login</p>
        </div>
      </div> : null}
      
    </div>
  )
}
export default NavBar;