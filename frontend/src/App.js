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
import PublicRoute from './components/route/publicRoute';
import PrivateRoute from './components/route/privateRoute';
import UnityRoute from './components/route/unityRoute';
import World from './components/world/World';
import './App.css';



function App() {

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/auth" 
                element={
                  <PublicRoute>
                    <Auth />
                  </PublicRoute>
                } />
        <Route path="/loginresult" 
               element={
               <PublicRoute>
                 <Login />
               </PublicRoute>
               } />
        <Route path="/logoutresult" 
               element={
               <PublicRoute>
                 <Logout />
               </PublicRoute>
               } />
        <Route path="/Save_The_Dogs" 
               element={
               <UnityRoute>
                 <SaveTheDogs />
               </UnityRoute>
               } />
        <Route path="/setprofile" 
               element={
               <PublicRoute>
                 <SetProfile />
               </PublicRoute>
               } />
        <Route path="/board/:category" 
               element={
               <PrivateRoute>
                 <BoardList />
               </PrivateRoute>
               } />
        <Route path="/board/detail/:boardId" 
               element={
               <PrivateRoute>
                 <BoardDetail />
               </PrivateRoute>
               } />
        <Route path="/create-board" 
               element={
               <PrivateRoute>
                 <CreateBoard />
               </PrivateRoute>
               } />
        <Route path="/world" element={<World />} />

      </Routes>
    </div>
  );
}

export default App;


