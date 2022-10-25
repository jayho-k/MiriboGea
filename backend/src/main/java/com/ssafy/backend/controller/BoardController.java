package com.ssafy.backend.controller;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.service.BoardService;
import io.swagger.annotations.ApiOperation;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.CreateArticleReq;
import com.ssafy.backend.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "게시글 생성", notes = "토큰을 이용해 유저가 게시글을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> createArticle(@RequestBody @Validated CreateArticleReq createArticleReq) {
        Optional<User> user = userService.getUserById(1L);
        boardService.createArticle(user.get(), createArticleReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
    }

    @PostMapping("/comment/{board_id}")
    public ResponseEntity<? extends BaseResponseBody> createComment(@PathVariable Long board_id) throws Exception {


        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "댓글작성이 완료되었습니다."));
    }
}
