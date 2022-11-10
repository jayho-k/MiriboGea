import React, { useEffect, useState } from "react";
import {  useParams } from "react-router-dom";
import BoardAPI from "../../api/BoardAPI";
import Comment from "./Comment";
function BoardDetail(){
  const { boardId } = useParams();
  const [boardInfo,setBoardInfo]=useState()
  const [commentList,setCommentList]=useState([])
  const [comment,setComment]=useState()
  const [likeState,setLikeState]=useState()
  useEffect(() => {
    async function load() {
      const detailResponse=await BoardAPI.getBoardDetail(boardId)
      console.log(detailResponse.data)
      const info=detailResponse.data.board
      setLikeState(detailResponse.data.likeState)
      setBoardInfo(info)

      const commentListResponse=await BoardAPI.getComment(boardId)
      const commentList=commentListResponse.data.commentList
      setCommentList(commentList)
      console.log(commentList)
    }
    load();
  }, []);

  async function commentWrite(){
    const body={
      content:comment
    }

    const result=await BoardAPI.createComment(boardId,body)
    setCommentList(result.data.commentList)
  }
  async function likeBoard(){
    const result=await BoardAPI.likeBoard(boardId)
    setLikeState(result.data.state)
    console.log(result)
    
  }

  if(boardInfo){
    return(
      <div>
        <p>title:{boardInfo.title}</p>
        <p>content:{boardInfo.content}</p>
        <p>category:{boardInfo.category}</p>
  
        <button onClick={()=>likeBoard()}>
          {
            likeState?"안좋아요":"좋아요"
          }</button>
        <p >댓글입력:</p>
        <input
            onChange={(e) => {
              setComment(e.target.value);
            }}
          />
        <button onClick={()=>commentWrite()}>
          create
        </button>
  
        {
          commentList.map((comment)=>{
            return(
              <Comment 
                boardId={boardId}
                comment={comment}
                setCommentList={setCommentList}
              />
            )
          })
        }
  
      </div>
    )
  }
  

}

export default BoardDetail;