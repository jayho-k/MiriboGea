package com.ssafy.backend.controller;


import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.UserRegisterRequest;
import com.ssafy.backend.response.BaseResponse;
import com.ssafy.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
    @GetMapping("/test")
    public String test(){
        return "asdf";
    }
    @PostMapping("/join")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userInfo) {
        User user = userService.registerUser(userInfo);
        return BaseResponse.success();
    }

}
