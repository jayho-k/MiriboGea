package com.ssafy.backend.service;


import com.ssafy.backend.common.util.scheduleJob.BannedJobData;
import org.quartz.SchedulerException;

public interface ScheduleService {
    void saveAndScheduleBannedJob(BannedJobData jobData) throws SchedulerException;
    void saveBannedJob(BannedJobData jobData);
    void scheduleBannedJob(BannedJobData jobData) throws SchedulerException;


}
