package com.ssafy.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Schedule {
    @Id
    @GeneratedValue
    private Long scheduleId;

    private Long userId;

    @Column(name = "trigger_at")
    private Long triggerAt;
}
