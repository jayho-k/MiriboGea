package com.ssafy.backend.repository;

import com.ssafy.backend.entity.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByTriggerAtGreaterThan(Long triggerAt);

}
