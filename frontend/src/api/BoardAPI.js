import axios from "axios";


const base = {
  baseUrl: process.env.REACT_APP_API_SERVER_BASE_URL,
  headers: {
    "Content-type": "application/json",
  },
};

const token=window.localStorage.getItem("token")
axios.defaults.headers.common['Authorization'] = `Bearer ${token}`

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
    return axios.post(`${base.baseUrl}/board`,body);
  }
  updateBoard(boardId,body) {
    // console.log("body", body);
    return axios.patch(`${base.baseUrl}/board/detail/${boardId}`,body);
  }
  deleteBoard(boardId) {
    // console.log("body", body);
    return axios.delete(`${base.baseUrl}/board/detail/${boardId}`);
  }

  likeBoard(boardId) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/board/like/${boardId}`);
  }
  createComment(commentId) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/board/comment/${commentId}`);
  }
  getComment(commentId) {
    // console.log("body", body);
    return axios.get(`${base.baseUrl}/board/comment/${commentId}`);
  }
  updateComment(commentId,body) {
    // console.log("body", body);
    return axios.put(`${base.baseUrl}/board/comment/${commentId}`,body);
  }
  deleteComment(commentId) {
    // console.log("body", body);
    return axios.delete(`${base.baseUrl}/board/comment/${commentId}`);
  }
  report(body) {
    // console.log("body", body);
    return axios.post(`${base.baseUrl}/board/report`,body);
  }
}

export default new BoardAPI();
