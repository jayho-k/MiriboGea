import React, { useEffect, useState } from "react";
import {  useParams,useNavigate } from "react-router-dom";

import BoardAPI from "../../api/BoardAPI";

function BoardList(){
  const { category } = useParams();
  const [boardList, setBoardList] = useState([]);
  const navigate=useNavigate()
  useEffect(() => {
    async function load() {
      const response=await BoardAPI.allBoard(category)
      const boardList=response.data.articleList
      setBoardList(boardList)
      console.lbog(response)
    }
    load();
  }, []);
  
  function Board({board}){
    console.log(board)
    return(
      <div>
        <p>title:{board.title}</p>
        <p>content:{board.content}</p>
        <p>category:{board.category}</p>
        <img></img>
      </div>
    )
  }
  console.log(boardList)

  return(
    <div>
      {
        boardList.map((board)=>{
          return(
            <div 
              key={board.id}
              onClick={()=>{
                navigate(`/board/detail/${board.id}`);
              }} 
            >
              <Board 
              board={board}
              />
            </div>
          )
          
        })
      }
    </div>
  )
}

export default BoardList;