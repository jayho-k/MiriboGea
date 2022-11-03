import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import BoardAPI from "../../api/BoardAPI";

function CreateBoard(){

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
    })
  }
  function setPicURL(picURL){
    setBoardInfo({
      ...boardInfo,
      picURL:picURL
    })
  }
  async function boardWrite(){
    const result=await BoardAPI.createBoard(boardInfo)
    console.log(result)
  }
  return (
    <div>
      <p >title:</p>
      <input
          onChange={(e) => {
            setTitle(e.target.value);
          }}
        />
      <p >content:</p>
      <input
          onChange={(e) => {
            setContent(e.target.value);
          }}
        />
      <p >category:</p>
      <input
          onChange={(e) => {
            setCategory(e.target.value);
          }}
        />
      <p >picURL:</p>
      <input
          onChange={(e) => {
            setPicURL(e.target.value);
          }}
        />
      <button onClick={()=>boardWrite()}>
        create
      </button>
    </div>
  );
}

export default CreateBoard;