import styles from "./css/Menu.module.css";
import React, { useState,useRef,useEffect } from 'react';
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { selectToken } from "../app/redux/userSlice";
import { uploadImageFile } from "../plugins/s3upload";
import BoardAPI from "../api/BoardAPI";
import Comment from "./board/Comment";
import hamburger from "../asset/img/hamburger.png";
import home from "../asset/img/home.png";
import logout from "../asset/img/logout.png";
import board from "../asset/img/board.png";
import photoUpload from "../asset/img/PhotoUpload.png";
import back from "../asset/img/back.png";
import Go from "../asset/img/Go.png";
import nolike from "../asset/img/nolike.png";
import redlike from "../asset/img/redlike.png";
import boardBack from "../asset/img/boardBack.png";
import send from "../asset/img/send.png";

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

function Board({ onClose }) {
  function handleClose() {
    onClose?.();
  }

  const [boardInfo, setBoardInfo] = useState();
  function setTitle(title){
    setBoardInfo({
      ...boardInfo,
      title:title
    })
  }
  function setContent(content){
    setBoardInfo({
      ...boardInfo,
      content:content
    })
  }
  function setCategory(category){
    setBoardInfo({
      ...boardInfo,
      category:category
    });
  }
  async function boardWrite(){
    const picURL=await uploadImageFile(uploadImage)
    const result=await BoardAPI.createBoard({...boardInfo,picURL:picURL})
    console.log(result)
    if (result.data.message==="success" && boardInfo.category==="mission2"){
      loadDetail(result.data.boardId)
      setBoardNum('2')
      setBoardList(result.data.articleList)
      setFileImage(photoUpload)
    }
  }

  const [boardList, setBoardList] = useState([]);

  const {category} = useParams();
  useEffect(() => {
    async function load() {
      const response=await BoardAPI.allBoard(category)
      const boardList=response.data.articleList
      setBoardList(boardList)
      console.log(boardList)
    }
    load();
  }, []);

  function Post({board}){
    return(
      <img className={styles.postImg} src={board.picURL} alt=""/>
    )
  }

  const [commentList,setCommentList]=useState([]);
  const [comment,setComment]=useState('');
  const [likeState,setLikeState]=useState();
  const [boardDetail,setBoardDetail]=useState()

  const loadDetail=async (boardId)=>{
    const detailResponse=await BoardAPI.getBoardDetail(boardId)
    const info=detailResponse.data.board
    console.log(info)
    setLikeState(detailResponse.data.likeState)
    setBoardDetail(info)
    const commentListResponse=await BoardAPI.getComment(boardId)
    const commentList=commentListResponse.data.commentList
    setCommentList(commentList)
    console.log(commentList)
  }

  async function commentWrite(boardId){
    const body={
      content:comment
    }
    const result=await BoardAPI.createComment(boardId,body)
    setCommentList(result.data.commentList)
  }
  async function likeBoard(boardId){
    const result=await BoardAPI.likeBoard(boardId)
    setLikeState(result.data.state)
    console.log(result)
  }


  // 미션2 내강아지자랑하기 게시판
  const [boardNum, setBoardNum] = useState('0');
  const photoInput = useRef();
  const handleClick = () => {
    photoInput.current.click();
  };

  const [fileImage, setFileImage] = useState(photoUpload);
  const [uploadImage, setUploadImage] = useState();

  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
    setUploadImage(e.target.files[0]);
  };

  return (
      <div className={styles.Modal} onClick={handleClose}>
        <div className={styles.BoardBody} onClick={(e) => e.stopPropagation()}>
          <div>
            <svg
              className={styles.BoardCloseBtn}
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

          {/* 미션2 강아지 자랑하기 */}
          {boardNum==='0' ?
            <div className={styles.ListBox}>
              {/* 카테고리박스 */}
              <div className={styles.CategoryBox}>
                <p className={styles.Category} >
                  자유게시판
                </p>
              </div>
                {/* 새 글 작성 */}
              <p onClick={()=>{setBoardNum('1');setCategory('mission2');}} className={styles.NewText}>+ 새 글 작성</p>
              <div className={styles.List}>
                {
                  boardList.map((board)=>{
                    if(board.category==="mission2"){
                      return(
                        <div 
                          key={board.id}
                          onClick={()=>{
                            loadDetail(board.id)
                            setBoardNum('2')
                          }} 
                        >
                          <Post 
                          board={board}
                          />
                        </div>
                      )
                    }
                    
                  })
                }
              </div>
            </div> 
            : null }

            {/*  미션2 내 강아지 자랑하기 게시글 작성 폼 */}
            {boardNum==='1' ?
            <div>
              <div className={styles.PhotoBox}>
                <img
                  onClick={handleClick}
                  className={styles.Photo}
                  src={fileImage}
                  alt="sample"
                  />
                <input
                  type="file"
                  name="imgUpload"
                  accept="image/*"
                  onChange={saveFileImage}
                  style={{ display: "none" }}
                  ref={photoInput}
                  />
              </div>
              <div className={styles.InputBox}>
                <p className={styles.InputCategory}>주제 : 자유게시판</p>
                <div>
                  <label className={styles.LabelFont} for="title">제목 : </label>
                  <input className={styles.InputTitle} id="title" 
                         onChange={(e) => {setTitle(e.target.value);}}
                         maxLength={25}/>
                </div>
                <div className={styles.ContextBox}>
                  <label className={styles.LabelFont} for="content">내용 : </label>
                  <textarea className={styles.InputContext} id="content" 
                            onChange={(e) => {setContent(e.target.value);}}
                            maxLength={1000}/>
                </div>
              </div>
              <img className={styles.backImg} src={back} alt="" onClick={()=>{setBoardNum('0');}}/>
              <img className={styles.goImg} src={Go} alt="" 
                   onClick={()=>{boardWrite();}}/>
            </div>
              :null
            }

            {/* 미션2 강아지 자랑하기 상세페이지 */}
            {boardNum==='2' && boardDetail ? 
              <div className={styles.DetailBox}>
                {console.log("boardDetail",boardDetail)}
                <img onClick={()=>{setBoardNum('0')}} className={styles.boardBack} src={boardBack} alt=""/>
                <p className={styles.detailTitle}>{boardDetail.title}</p>
                <div><img className={styles.detailImg} src={boardDetail.picURL} alt=""/></div>
                {likeState ? 
                  <div className={styles.heartBox}>
                    <img className={styles.heartImg} onClick={()=>likeBoard(boardDetail.id)} src={redlike} alt=""/>
                    <p className={styles.detailUser}>{boardDetail.user.nickname}</p>
                  </div> : 
                  <div className={styles.heartBox}>
                    <img className={styles.heartImg} onClick={()=>likeBoard(boardDetail.id)} src={nolike} alt=""/>
                    <p className={styles.detailUser}>{boardDetail.user.nickname}</p>
                  </div>
                }
                <p className={styles.detailContent}>{boardDetail.content}</p>
                <div className={styles.commentBox}>
                  <input
                    className={styles.commentInput}
                    placeholder="댓글 추가..."
                    maxLength={100}
                    onChange={(e) => {
                      setComment(e.target.value);
                    }}
                    value={comment}
                    />
                    { comment==='' ? 
                      <img className={styles.sendImg} src={send} alt=""/>:
                      <img className={styles.sendImg} onClick={()=>{commentWrite(boardDetail.id);setComment('')}} src={send} alt=""/>
                    }
                </div>
          
                {
                  commentList.map((comment)=>{
                    return(
                      <Comment 
                        boardId={boardDetail.id}
                        comment={comment}
                        setCommentList={setCommentList}
                      />
                    )
                  })
                }
              </div> : null}
          <div style={{ position: "absolute", left: "32px", top: "72px" }}>
            {/* <p className={styles.BoardText}>현재 로그인이 되어있지않습니다.</p> */}
          </div>
        </div>
      </div> 
  );
}

function Menu(){
  const [openModal, setOpenModal] = useState(false);
  const showModal = () => {
    setOpenModal(true);
  };
  const [openBoard, setOpenBoard] = useState(false);
  const showBoard = () => {
    setOpenBoard(true);
  };

  const [state,setState] = useState(false);
  const onClick = () => {
    setState(!state)
  }
  const goToMain = () => {
    window.location.replace("/")
  };
  return(
  <div className={styles.menu}>
    <img onClick={goToMain} className={state ? styles.show1 : styles.hidden} src={home} alt=""/>
    <img onClick={showBoard} className={state ? styles.show2 : styles.hidden} src={board} alt=""/>
    <img onClick={showModal} className={state ? styles.show3 : styles.hidden} src={logout} alt=""/>
    <img onClick={onClick} className={styles.bar} src={hamburger} alt=""/>
    {openModal && (
          <Modal
            open={openModal}
            onClose={() => {
              setOpenModal(false);
            }}
          />
        )}
    {openBoard && (
          <Board
            open={openBoard}
            onClose={() => {
              setOpenBoard(false);
            }}
          />
        )}
  </div>
  )
}
export default Menu;