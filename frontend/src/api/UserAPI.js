import axios from "axios";
const base = {
  baseUrl: process.env.REACT_APP_API_SERVER_BASE_URL,
  headers: {
    "Content-type": "application/json",
  },
};

class UserAPI {
  kakaoLogin(code) {
    const body = {
      code: code,
    };
    return axios.post(`${base.baseUrl}/user/login`, body);
  }

  join(body) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/user/join`, body);
  }
}

export default new UserAPI();
