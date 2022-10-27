package com.ssafy.backend.controller;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.UserBoardLike;
import com.ssafy.backend.response.ArticleDetailRes;
import com.ssafy.backend.response.ArticleLikeRes;
import com.ssafy.backend.response.ArticleListRes;
import com.ssafy.backend.entity.Comment;
import com.ssafy.backend.request.CreateCommentReq;
import com.ssafy.backend.request.UpdateCommentReq;
import com.ssafy.backend.response.GetCommentRes;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/{category}")
    @ApiOperation(value = "게시글 전체조회", notes = "카테고리별 게시글 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<ArticleListRes> getArticleList(@PathVariable("category") String category) {
        List<Board> articleList = boardService.getArticleListByCategory(category);
        return ResponseEntity.status(200).body(ArticleListRes.of(200, "success", articleList));
    }

    @GetMapping("/detail/{boardId}")
    @ApiOperation(value = "게시글 상세조회", notes = "선택한 게시글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<ArticleDetailRes> getArticleDetail(@PathVariable("boardId") Long boardId) {
        Board board = boardService.getBoardById(boardId).get();
        return ResponseEntity.status(200).body(ArticleDetailRes.of(200,"success",board));
    }

    @PostMapping
    @ApiOperation(value = "게시글 생성", notes = "토큰을 이용해 유저가 게시글을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> createArticle(@RequestBody @Validated CreateArticleReq createArticleReq) {
        // user 인증방식으로 바꾸기
        Long userId = 1L;
        User user = userService.getUserById(userId).get();
        boardService.createArticle(user, createArticleReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
    }

    @PatchMapping("/detail/{boardId}")
    @ApiOperation(value = "게시글 수정", notes = "토큰을 이용해 유저가 게시글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updateArticle(@PathVariable Long boardId, @RequestBody Map<Object,Object> objectMap) {
        Long writerId = boardService.getBoardById(boardId).get().getUser().getId();
        // user 인증방식으로 바꾸기
        Long userId = 1L;
        if (userId == writerId){
            boardService.updateArticle(boardId, objectMap);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"success"));
        }
        throw new IllegalArgumentException("게시글 작성자가 아닙니다.");
    }

    @DeleteMapping("/detail/{boardId}")
    @ApiOperation(value = "게시글 삭제", notes = "토큰을 이용해 유저가 게시글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> deleteArticle(@PathVariable Long boardId) {
        Long writerId = boardService.getBoardById(boardId).get().getUser().getId();
        // user 인증방식으로 바꾸기
        Long userId = 1L;
        if (userId == writerId) {
            boardService.deleteArticle(boardId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
        }
        throw new IllegalStateException("게시글 작성자가 아닙니다.");
    }


    @PostMapping("/like/{boardId}")
    @ApiOperation(value = "게시글 좋아요", notes = "토큰을 이용해 유저가 게시글에 좋아요 혹은 좋아요 취소를 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<ArticleLikeRes> likeArticle(@PathVariable Long boardId) {
        // user 인증방식으로 바꾸기
        Long userId = 1L;
        // Optional 로 받아오기
        Optional<UserBoardLike> userBoardLike = boardService.getUserBoardLike(boardId, userId);
        // 있으면 삭제 후 return 0
        if (userBoardLike.isPresent()){
            Long userBoardLikeId = userBoardLike.get().getId();
            boardService.deleteArticleLike(userBoardLikeId);
            return ResponseEntity.status(200).body(ArticleLikeRes.of(200,"success",false));
        }
        // null 값이면 새로 생성 후 return 1
        boardService.createArticleLike(userService.getUserById(userId).get(), boardService.getBoardById(boardId).get());
        return ResponseEntity.status(200).body(ArticleLikeRes.of(200,"success",true));
    }

    @PostMapping("/comment/{board_id}")
        public ResponseEntity<? extends BaseResponseBody> createComment(@PathVariable Long board_id, @RequestBody @Validated CreateCommentReq createCommentReq) {
            Optional<User> user = userService.getUserById(1L);
            Optional<Board> board = boardService.getBoardById(board_id);
            boardService.createComment(user.get(), board.get(), createCommentReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "댓글작성이 완료되었습니다."));
    }

    @GetMapping("/comment/{board_id}")
    public ResponseEntity<? extends GetCommentRes> getComment(@PathVariable Long board_id) {
        List<Comment> commentList = boardService.getComment(board_id);
        return ResponseEntity.status(200).body(GetCommentRes.of(commentList, 200, "success"));
    }

    @PutMapping("/comment/{comment_id}")
    public ResponseEntity<? extends BaseResponseBody> updateComment(@PathVariable Long comment_id, @RequestBody @Validated UpdateCommentReq updateCommentReq) {
//        Optional<User> user = userService.getUserById(1L);
        Optional<Comment> comment = boardService.getCommentById(comment_id);
        boardService.updateComment(comment.get(), updateCommentReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
    }

    @DeleteMapping("/comment/{comment_id}")
    public ResponseEntity<? extends BaseResponseBody> deleteComment(@PathVariable Long comment_id) {
        boardService.deleteComment(comment_id);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
    }
}
