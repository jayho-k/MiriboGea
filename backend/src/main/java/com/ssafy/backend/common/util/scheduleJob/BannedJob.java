package com.ssafy.backend.common.util.scheduleJob;

import com.ssafy.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class BannedJob implements Job {
    private final UserService userService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobData = context.getJobDetail().getJobDataMap();
        log.info("실행 {}",jobData);
        userService.release(BannedJobData.of(jobData));
    }

    public static JobDetail newChallengeJob(BannedJobData bannedJobData) {
        return JobBuilder.newJob(BannedJob.class).setJobData(bannedJobData.toJobData()).build();
    }

    public static Trigger newTrigger(BannedJobData bannedJobData) {
        Date date = bannedJobData.toDate();
        return TriggerBuilder.newTrigger().startAt(date).endAt(date).build();
    }
}
