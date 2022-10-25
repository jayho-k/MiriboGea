import React from 'react';

import { Routes, Route } from "react-router-dom";
import Auth from "./components/auth/Auth";
import Login from "./components/auth/Login";
import Logout from "./components/auth/Logout";
import SetProfile from "./components/auth/SetProfile";
import './App.css';



function App() {

  return (
    <div className="App">
      <Routes>
        <Route path="/auth" element={<Auth />} />
        <Route path="/loginresult" element={<Login />} />
        <Route path="/logoutresult" element={<Logout />} />
        <Route path="/setprofile" element={<SetProfile />} />
      </Routes>
    </div>
  );
}

export default App;
