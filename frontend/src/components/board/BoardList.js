import React, { useEffect, useState } from "react";
import {  useParams } from "react-router-dom";

import BoardAPI from "../../api/BoardAPI";

function BoardList(){
  const { category } = useParams();
  const [boardList, setBoardList] = useState();
  useEffect(() => {
    async function load() {

      const response=await BoardAPI.allBoard(category)
      const boardList=response.data.articleList
      setBoardList(boardList)
      console.log(boardList)
    }
    load();
  }, []);

  return(
    <div>
      {
        boardList.map((board)=>{
          return(
            <div>
              <p>
                title:{board.title}
              </p>
              <p>
                content:{board.title}
              </p>
              <p>
                category:{board.title}
              </p>
              <img></img>
            </div>
          )
        })
      }
    </div>
  )
}

export default BoardList;