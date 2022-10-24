package com.ssafy.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @Column(name="user_id")
    private Long id;
    private String email;
    private String nickname;
    private int missionProgress;
    private boolean isAdmin;
    private boolean isBanned;
    private String profileURL;
    private int warningCount;
}
