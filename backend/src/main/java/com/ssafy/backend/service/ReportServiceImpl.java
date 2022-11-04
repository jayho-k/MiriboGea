package com.ssafy.backend.service;

import com.ssafy.backend.entity.Report;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.repository.ReportRepository;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.request.ReportCheckReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<Report> getReport(String state) {
        // 관리자 유저인지 확인해줘야함
        // state : unread,warning,notWarning
        return  reportRepository.findByState(state);
    }

    @Override
    public void checkReport(Report report, ReportCheckReq reportCheckReq) {
        // 관리자 유저인지 판단해주어여 함
        // state : unread,warning , notWarning
        report.setState(reportCheckReq.getState());
        reportRepository.save(report);
    }
    @Override
    public Optional<Report> getReportById(Long report_id) {
        return reportRepository.findById(report_id);
    }
}
