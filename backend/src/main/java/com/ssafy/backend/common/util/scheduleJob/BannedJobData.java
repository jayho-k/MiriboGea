package com.ssafy.backend.common.util.scheduleJob;


import com.ssafy.backend.entity.Schedule;
import com.ssafy.backend.request.UserScheduleRequest;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.quartz.JobDataMap;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
@ToString
public class BannedJobData {

    private Long userId;
    private Long triggerAt;

    public JobDataMap toJobData() {
        JobDataMap jobData = new JobDataMap();
        jobData.put("userId", userId);
        return jobData;
    }

    public Date toDate() {
        return Date.from(Instant.ofEpochSecond(triggerAt));
    }

    public Schedule toScheduleEntity() {
        return Schedule.builder()
                .userId(userId)
                .triggerAt(triggerAt)
                .build();
    }

    public static BannedJobData of(UserScheduleRequest userScheduleRequest) {
        return BannedJobData.builder()
                .userId(userScheduleRequest.getUserId())
                .triggerAt(userScheduleRequest.getTriggerAt())
                .build();
    }

    public static BannedJobData of(Schedule schedule) {
        return BannedJobData.builder()
                .userId(schedule.getUserId())
                .triggerAt(schedule.getTriggerAt())
                .build();
    }

    public static BannedJobData of(JobDataMap jobData) {
        return BannedJobData.builder()
                .userId(jobData.getLong("userId"))
                .build();
    }
}
