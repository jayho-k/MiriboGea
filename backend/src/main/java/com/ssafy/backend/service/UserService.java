package com.ssafy.backend.service;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.UserRegisterRequest;

public interface UserService {
    User registerUser(UserRegisterRequest userRegisterInfo);
}
