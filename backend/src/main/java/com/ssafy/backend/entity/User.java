package com.ssafy.backend.entity;

import com.ssafy.backend.request.UserRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;
    private String email;
    private String nickname;
    private int missionProgress;
    private boolean isAdmin;
    private boolean isBanned;
    private String profileURL;
    private int warningCount;

    public static User of(UserRegisterRequest userRegisterRequest){
        return User.builder()
                .email(userRegisterRequest.getEmail())
                .nickname(userRegisterRequest.getNickname())
                .missionProgress(0)
                .isAdmin(false)
                .isBanned(false)
                .profileURL(userRegisterRequest.getProfileURL())
                .warningCount(0)
                .build();
    }

}
