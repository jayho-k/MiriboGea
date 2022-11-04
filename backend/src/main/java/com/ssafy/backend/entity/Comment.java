package com.ssafy.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.backend.repository.CommentRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private User user;

//    public Comment(CommentRepository commentRepository) {
//
//        // this.id = commentRepository.getId(); ==> 이러한 것들을 자동으로 생성해주는 함수이다.
//        // Properties를 copy하는데 여기에다가 copy할 것이다. 라는 뜻
//        BeanUtils.copyProperties(commentRepository, this);
//    }
}
