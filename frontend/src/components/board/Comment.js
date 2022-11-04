import React, { useEffect, useState } from "react";

import BoardAPI from "../../api/BoardAPI";
function Comment({boardId,comment,setCommentList}){
  const [input,setInput]=useState()

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
  }

  async function deleteComment(commentId){
    const result=await BoardAPI.deleteComment(boardId,commentId)
    setCommentList(result.data.commentList)
  }

  return(
    <div>
      <p>작성자:{comment.user.nickname}</p>
      <p>content:{comment.content}</p>
      <input onChange={(e) => {
          setInput(e.target.value);
        }}></input>
      <button onClick={()=>updateComment(comment.id)}>수정</button>
      <button onClick={()=>deleteComment(comment.id)}>삭제</button>
    </div>
  )
  

}

export default Comment;