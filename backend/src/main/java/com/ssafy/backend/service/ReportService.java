package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Report;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.ReportArticleReq;

import java.util.List;

public interface ReportService {
    void reportArticle(User user, Board board, ReportArticleReq reportArticleReq);

    List<Report> GetReport(User user, String state);
}
