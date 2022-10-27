package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Report;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.ReportArticleReq;
import com.ssafy.backend.request.ReportCheckReq;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    void reportArticle(User user, Board board, ReportArticleReq reportArticleReq);

    List<Report> GetReport(User user, String state);

    void checkReport(User user, Report report,ReportCheckReq reportCheckReq);

    Optional<Report> getReportById(Long report_id);
}
