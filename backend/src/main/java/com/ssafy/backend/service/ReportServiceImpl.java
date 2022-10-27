package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Report;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.repository.ReportRepository;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.request.ReportArticleReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        report.setState(reportArticleReq.getState());
        reportRepository.save(report);
    }




}
