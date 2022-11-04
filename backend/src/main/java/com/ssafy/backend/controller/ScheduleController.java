package com.ssafy.backend.controller;

import com.ssafy.backend.common.util.scheduleJob.BannedJobData;
import com.ssafy.backend.request.UserScheduleRequest;
import com.ssafy.backend.response.BaseResponse;
import com.ssafy.backend.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/{user_id}")
    public ResponseEntity<?> userBanSchedule(@RequestBody UserScheduleRequest userScheduleRequest) {

        log.info("스케줄 요청: {}", userScheduleRequest);
        try {
            scheduleService.saveAndScheduleBannedJob(BannedJobData.of(userScheduleRequest));

        } catch (Exception e) {
            log.error("유저 스케줄 추가 실패", e);
            return BaseResponse.fail("유저 스케줄 추가 실패");
        }

        return BaseResponse.success();
    }

}
