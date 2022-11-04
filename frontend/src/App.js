import React from 'react';

import { Routes, Route } from "react-router-dom";
import MainPage from "./components/MainPage";
import Auth from "./components/auth/Auth";
import Login from "./components/auth/Login";
import Logout from "./components/auth/Logout";
import SetProfile from "./components/auth/SetProfile";
import BoardDetail from './components/board/BoardDetail';
import BoardList from './components/board/BoardList';
import CreateBoard from './components/board/CreateBoard';
import SaveTheDogs from './components/SaveTheDogs';
import './App.css';



function App() {

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/auth" element={<Auth />} />
        <Route path="/loginresult" element={<Login />} />
        <Route path="/logoutresult" element={<Logout />} />
        <Route path="/Save_The_Dogs" element={<SaveTheDogs />} />
        <Route path="/setprofile" element={<SetProfile />} />
        <Route path="/board/:category" element={<BoardList />} />
        <Route path="/board/:id" element={<BoardDetail />} />
        <Route path="/create-board" element={<CreateBoard />} />
      </Routes>
    </div>
  );
}

export default App;
