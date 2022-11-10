import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import BoardAPI from "../../api/BoardAPI";

function CreateBoard(){
  const options = [
    {value: '', text: '--주제를 선택하세요.--'},
    {value: 'dog', text: '내 강아지 자랑하기'},
    {value: 'mission', text: '미션3 주인찾아주기'},
  ];
  const [selected, setSelected] = useState(options[0].value);
  
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
    setSelected(category);
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
      <select value={selected} onChange={(e) => {
            setCategory(e.target.value);
          }}>
        {options.map(option => (
          <option key={option.value} value={option.value}>
            {option.text}
          </option>
        ))}
      </select>
      {/* <select onChange={(e) => {
            setCategory(e.target.value);
          }}>
        <option value="dog">내 강아지 자랑하기</option>
        <option value="mission">미션3 주인찾아주기</option>
      </select> */}
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