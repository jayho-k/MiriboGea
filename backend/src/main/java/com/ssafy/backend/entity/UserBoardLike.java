package com.ssafy.backend.entity;

import javax.persistence.*;

@Entity
public class UserBoardLike {

    @Id
    @Column(name = "user_board_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


}
