package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Report;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.repository.ReportRepository;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.request.ReportArticleReq;
import com.ssafy.backend.request.ReportCheckReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override

    public void reportArticle(User user, Board board, ReportArticleReq reportArticleReq) {
        Report report = new Report();
        report.setReporter(user);
        report.setBoard(board);
        report.setContent(reportArticleReq.getContent());
        report.setState("unread");
        reportRepository.save(report);
    }

    @Override
    public List<Report> GetReport(User user, String state) {
        // 관리자 유저인지 확인해줘야함
        // state : unread,warning,notWarning
        return  reportRepository.findByState(state);
    }

    @Override
    public void checkReport(User user, Report report, ReportCheckReq reportCheckReq) {
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
