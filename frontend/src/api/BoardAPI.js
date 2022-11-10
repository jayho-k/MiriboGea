import Axios from "axios";

const token = window.localStorage.getItem("token");
const base = {
  baseUrl: process.env.REACT_APP_API_SERVER_BASE_URL,
};
const axios = Axios.create({
  headers: {
    // "Content-type": "application/json",
    Authorization: `Bearer ${token}`,
  },
});

class BoardAPI {
  allBoard(category) {
    return axios.get(`${base.baseUrl}/board/${category}`);
  }

  getBoardDetail(boardId) {
    // console.log("body", body);
    return axios.get(`${base.baseUrl}/board/detail/${boardId}`);
  }
  getMyBoardList() {
    // console.log("body", body);
    return axios.get(`${base.baseUrl}/board/myboard`);
  }
  createBoard(body) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/board`, body);
  }
  updateBoard(boardId, body) {
    // console.log("body", body);
    return axios.patch(`${base.baseUrl}/board/detail/${boardId}`, body);
  }
  deleteBoard(boardId) {
    // console.log("body", body);
    return axios.delete(`${base.baseUrl}/board/detail/${boardId}`);
  }

  likeBoard(boardId) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/board/like/${boardId}`);
  }
  createComment(boardId, body) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/board/comment/${boardId}`, body);
  }
  getComment(boardId) {
    // console.log("body", body);
    return axios.get(`${base.baseUrl}/board/comment/${boardId}`);
  }
  updateComment(boardId, commentId, body) {
    // console.log("body", body);
    return axios.put(
      `${base.baseUrl}/board/comment/${boardId}/${commentId}`,
      body
    );
  }
  deleteComment(boardId, commentId) {
    // console.log("body", body);
    return axios.delete(
      `${base.baseUrl}/board/comment/${boardId}/${commentId}`
    );
  }
  report(body) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/board/report`, body);
  }
  story3check() {
    // console.log("body", body);
    return axios.get(`${base.baseUrl}/board/check/leaflet`);
  }
}

export default new BoardAPI();
