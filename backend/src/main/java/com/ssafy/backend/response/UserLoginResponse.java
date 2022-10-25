package com.ssafy.backend.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private Boolean visited;
    private String jwt;
    private UserResponse user;
}
