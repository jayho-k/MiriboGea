package com.ssafy.backend.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserRegisterRequest {
    private String email;
    private String nickname;
    private String profileURL;
}
