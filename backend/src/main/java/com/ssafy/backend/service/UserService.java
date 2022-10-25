package com.ssafy.backend.service;

import com.ssafy.backend.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);
}
