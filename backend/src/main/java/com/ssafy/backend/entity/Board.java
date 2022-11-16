package com.ssafy.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Board {
    public static class BoardSimple{
        public BoardSimple(Board board,Long count) {
            title = board.getTitle();
            picURL = board.getPicURL();
            likeCount = count;
            user = board.getUser();
        }
        String title;
        String picURL;
        Long likeCount;
        User user;
    }


    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private String title;

    @Column(length=1000)
    private String content;
    private String category;
    private String picURL;
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
