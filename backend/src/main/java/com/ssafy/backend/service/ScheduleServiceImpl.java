package com.ssafy.backend.service;


import com.ssafy.backend.common.util.scheduleJob.BannedJob;
import com.ssafy.backend.common.util.scheduleJob.BannedJobData;
import com.ssafy.backend.entity.Schedule;
import com.ssafy.backend.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final Scheduler scheduler;

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void initSchedule() throws SchedulerException {

        log.info("초기 스케줄 데이터 로드");

        List<Schedule> schedules = scheduleRepository.findAllByTriggerAtGreaterThan(Instant.now().getEpochSecond());

        for (Schedule schedule : schedules) {
            scheduleBannedJob(BannedJobData.of(schedule));
        }
    }

    @Override
    public void saveAndScheduleBannedJob(BannedJobData jobData) throws SchedulerException {
        saveBannedJob(jobData);
        scheduleBannedJob(jobData);
    }

    @Override
    public void saveBannedJob(BannedJobData jobData) {
        scheduleRepository.save(jobData.toScheduleEntity());
    }

    @Override
    public void scheduleBannedJob(BannedJobData jobData) throws SchedulerException {
        scheduler.scheduleJob(BannedJob.newChallengeJob(jobData), BannedJob.newTrigger(jobData));
    }



    private static class NumGen {
        private long num;
        private final long interval = 15L;

        NumGen(long initNum) {
            num = initNum - interval;
        }

        long next() {
            return num += interval;
        }

        long get() {
            return num;
        }
    }



}
