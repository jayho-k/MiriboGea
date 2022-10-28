package com.ssafy.backend.entity;


import com.ssafy.backend.request.UserRegisterRequest;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;
    private String email;
    private String nickname;
    private int missionProgress;

    private String role;
    private boolean isBanned;

    private String profileURL;
    private int warningCount;

    public static User of(UserRegisterRequest userRegisterRequest){
        return User.builder()
                .email(userRegisterRequest.getEmail())
                .nickname(userRegisterRequest.getNickname())
                .missionProgress(0)
                .role("USER")
                .isBanned(false)
                .profileURL(userRegisterRequest.getProfileURL())
                .warningCount(0)
                .build();
    }

}
