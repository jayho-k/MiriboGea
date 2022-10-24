package com.ssafy.backend.response.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @Column(name="report_id")
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

}
