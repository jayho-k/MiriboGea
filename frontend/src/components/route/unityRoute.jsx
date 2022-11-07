import React from 'react';
import { Navigate } from 'react-router-dom';
import { useSelector } from "react-redux";
import { selectToken } from '../../app/redux/userSlice';

const UnityRoute = ({ children }) => {
  const token = useSelector(selectToken);
  return !token ? <Navigate to='/auth' /> : children;
};

export default UnityRoute;