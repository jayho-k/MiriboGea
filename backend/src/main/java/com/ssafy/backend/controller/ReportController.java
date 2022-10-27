package com.ssafy.backend.controller;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.ReportArticleReq;
import com.ssafy.backend.service.BoardService;
import com.ssafy.backend.service.ReportService;
import com.ssafy.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final BoardService boardService;
    private final UserService userService;
    private final ReportService reportService;

    @PostMapping("/{board_id}")
    public ResponseEntity<? extends BaseResponseBody> ReportArticle(@PathVariable Long board_id, @RequestBody @Validated ReportArticleReq reportArticleReq) {

        Optional<User> user = userService.getUserById(1L);
        Optional<Board> board = boardService.getBoardById(board_id);
        reportService.reportArticle(user.get(),board.get(), reportArticleReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
    }
}
