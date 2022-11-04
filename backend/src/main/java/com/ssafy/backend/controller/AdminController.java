package com.ssafy.backend.controller;

import com.ssafy.backend.common.auth.AppUserDetails;
import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Report;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.ReportRepository;
import com.ssafy.backend.request.ReportCheckReq;
import com.ssafy.backend.response.GetReportRes;
import com.ssafy.backend.service.BoardService;
import com.ssafy.backend.service.ReportService;
import com.ssafy.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final ReportService reportService;


    @GetMapping("/report")
    public ResponseEntity<? extends GetReportRes> GetReport(@RequestParam("state") String state) {
        List<Report> reportList = reportService.getReport(state);
        return ResponseEntity.status(200).body(GetReportRes.of(reportList,200, "success"));
    }
    @PutMapping("/report/check/{report_id}")
    public ResponseEntity<? extends BaseResponseBody> CheckReport(@PathVariable Long report_id, @RequestBody @Validated ReportCheckReq reportCheckReq) {
        Optional<Report> report = reportService.getReportById(report_id);
        reportService.checkReport(report.get(),reportCheckReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
    }


}
