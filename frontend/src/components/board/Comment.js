import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import styles from "../css/Comment.module.css";
import BoardAPI from "../../api/BoardAPI";
import deleteImg from "../../asset/img/delete.png";
import fixImg from "../../asset/img/fix.png";
import send from "../../asset/img/send.png";
import { selectUser } from "../../app/redux/userSlice";

function Comment({boardId,comment,setCommentList}){
  const [state,setState]=useState(false);
  const fix = () => {
    setState(true);
  }
  const [input,setInput]=useState(comment.content);
  const user = useSelector(selectUser);
  // useEffect(() => {
  //   async function load() {
  //     const detailResponse=await BoardAPI.getBoardDetail(boardId)
  //     const info=detailResponse.data.board
  //     console.log(info)
  //     setBoardInfo(info)

  //     const commentListResponse=await BoardAPI.getComment(boardId)
  //     const commentList=commentListResponse.data.commentList
  //     setCommentList(commentList)
  //     console.log(commentList)
  //   }
  //   load();
  // }, []);

  async function updateComment(commentId){
    const body={
      content:input
    }
    const result=await BoardAPI.updateComment(boardId,commentId,body)
    setCommentList(result.data.commentList)
    console.log(result.data.commentList)
  }

  async function deleteComment(commentId){
    const result=await BoardAPI.deleteComment(boardId,commentId)
    setCommentList(result.data.commentList)
  }

  return(
    <div className={styles.commentBox}>
      <div className={styles.userInfo}>
        <img className={styles.userImg} src={comment.user.profileURL} alt=""/>
        <p className={styles.userNickname}>{comment.user.nickname}</p>
      </div>
      <div className={styles.userCommentBox}>
        {state ? 
          <div className={styles.commentFixBox}>
            <input
              type="text"
              className={styles.fixInput} 
              onBlur={()=>{setState(false);}}
              onChange={(e) => {setInput(e.target.value);}}
              value={input}/> 
            <img
              onMouseDown={()=>{updateComment(comment.id);setState(false);}} 
              style={{width:"24px",marginTop:"8px"}}src={send} alt=""/>
          </div>
          :
          <p className={styles.userComment}>{comment.content}</p>
        }
        {comment.user.id === user.id && !state? 
          <div className={styles.fixBox}>
            <img
              onClick={fix} 
              className={styles.commentImg} src={fixImg} alt=""/>
            <img
              onClick={()=>deleteComment(comment.id)} 
              className={styles.commentImg} src={deleteImg} alt=""/>
          </div>:null}
        {/* <input
              maxLength={100}
              onChange={(e) => {
              setInput(e.target.value);
            }}></input>
        <button onClick={()=>updateComment(comment.id)}>수정</button>
        <button onClick={()=>deleteComment(comment.id)}>삭제</button> */}
      </div>
    </div>
  )
  

}

export default Comment;