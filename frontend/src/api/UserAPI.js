import Axios from "axios";

const token=window.localStorage.getItem("token")
const base = {
  baseUrl: process.env.REACT_APP_API_SERVER_BASE_URL,
  
};
const axios = Axios.create({
  headers: {
    // "Content-type": "application/json",
    "Authorization":`Bearer ${token}`,
  },
})

class UserAPI {
  kakaoLogin(code) {
    const body = {
      code: code,
    };
    return Axios.post(`${base.baseUrl}/user/login`, body);
  }

  join(body) {
    // console.log("body", body);
    return Axios.post(`${base.baseUrl}/user/join`, body);
  }

  mypage() {
    return axios.get(`${base.baseUrl}/user/mypage`);
  }

  progress(body) {
    return axios.put(`${base.baseUrl}/user/progress`,body);
  }
}

export default new UserAPI();
