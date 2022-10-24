package com.ssafy.backend.response.entity;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @Column(name = "comment_id")
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
