import React, {  useState,useRef } from "react";
import { uploadImageFile } from "../../plugins/s3upload";
import BoardAPI from "../../api/BoardAPI";

function CreateBoard(){

  const photoInput = useRef();
  const handleClick = () => {
    photoInput.current.click();
  };

  const [fileImage, setFileImage] = useState();
  const [uploadImage, setUploadImage] = useState();

  const saveFileImage = (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0]));
    setUploadImage(e.target.files[0]);
  };

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
    const picURL=await uploadImageFile(uploadImage)
    console.log(picURL)
    setPicURL(picURL)
    console.log(boardInfo)
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
      <img
          onClick={handleClick}
          style={{ width: "80px", height: "80px", borderRadius: "100px" }}
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
      <button onClick={()=>boardWrite()}>
        create
      </button>
    </div>
  );
}

export default CreateBoard;