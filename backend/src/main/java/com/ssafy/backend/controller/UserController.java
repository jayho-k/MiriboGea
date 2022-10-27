package com.ssafy.backend.controller;


import com.ssafy.backend.common.util.JwtTokenUtil;
import com.ssafy.backend.common.util.KakaoApi;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.UserRegisterRequest;
import com.ssafy.backend.response.BaseResponse;
import com.ssafy.backend.response.UserLoginResponse;
import com.ssafy.backend.response.UserResponse;
import com.ssafy.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KakaoApi kakaoApi;
    private final UserService userService;


    @PostMapping("/join")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userInfo) {
        User user = userService.registerUser(userInfo);
        return BaseResponse.success();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
        String code = requestBody.get("code");
        logger.debug("code: {}", code);

        if (code == null || code.trim().isEmpty()) {
            return BaseResponse.fail("noCode");
        }

        String accessToken = kakaoApi.getAccessToken(code);
        logger.debug("accessToken: {}", accessToken);

        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
        logger.debug("login info: {}", userInfo.toString());

        String email = (String) userInfo.get("email");

        if (userInfo.get("email") == null) {
            return BaseResponse.fail("noEmail");
        }

        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            UserResponse userResponse = UserResponse.builder()
                    .email(email)
                    .profileURL((String) userInfo.get("profile_image"))
                    .nickname((String) userInfo.get("nickname"))
                    .build();
            return BaseResponse.success(UserLoginResponse.builder()
                    .visited(false)
                    .user(userResponse)
                    .build());
        }

        String token = JwtTokenUtil.getToken(email);
        logger.debug("\n\ntoken:{}\n\n", token);
        return BaseResponse.success(UserLoginResponse.builder()
                .visited(true)
                .jwt(token)
                .user(UserResponse.of(userOptional.get()))
                .build());
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> userInfo(@RequestHeader("userId") Long userId){
        logger.debug("userId: {}", userId);
        User user=userService.findOne(userId).orElseGet(()->new User());
        return  BaseResponse.success(UserResponse.of(user));
    }
}
