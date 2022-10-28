package com.ssafy.backend.service;

import com.ssafy.backend.entity.Report;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.ReportCheckReq;

import java.util.List;
import java.util.Optional;

public interface ReportService {


    List<Report> GetReport(User user, String state);

    void checkReport(User user, Report report, ReportCheckReq reportCheckReq);

    Optional<Report> getReportById(Long report_id);
}
