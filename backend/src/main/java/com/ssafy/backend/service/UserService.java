package com.ssafy.backend.service;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.UserRegisterRequest;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);
    User registerUser(UserRegisterRequest userRegisterInfo);
    Optional<User> getUserByEmail(String email);
    Optional<User> findOne(Long id);

}
