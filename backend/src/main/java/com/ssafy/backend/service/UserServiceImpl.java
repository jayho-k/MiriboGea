package com.ssafy.backend.service;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User registerUser(UserRegisterRequest userRegisterInfo) {
        User user = User.of(userRegisterInfo);
        return userRepository.save(user);
    }
}
