package com.ssafy.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Report {
    @Id
    @GeneratedValue
    @Column(name="report_id")
    private Long id;
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    private String state;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    @JsonIgnore
    private Board board;
}
