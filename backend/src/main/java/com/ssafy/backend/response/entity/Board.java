package com.ssafy.backend.response.entity;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Board {

    @Id
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    private String category;
    private String picURL;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
