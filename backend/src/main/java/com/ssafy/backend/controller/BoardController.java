package com.ssafy.backend.controller;

import com.ssafy.backend.common.auth.AppUserDetails;
import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.UserBoardLike;

import com.ssafy.backend.request.ReportArticleReq;
import com.ssafy.backend.response.*;
import com.ssafy.backend.entity.Comment;
import com.ssafy.backend.request.CreateCommentReq;
import com.ssafy.backend.request.UpdateCommentReq;
import com.ssafy.backend.service.BoardService;
import com.ssafy.backend.service.ReportService;
import io.swagger.annotations.ApiOperation;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.CreateArticleReq;
import com.ssafy.backend.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@CrossOrigin("*")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;
    private final ReportService reportService;

    @GetMapping("/{category}") // ex) http://localhost:8080/api/board/freeBoard?page=0
    @ApiOperation(value = "게시글 전체조회", notes = "카테고리별 게시글 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<ArticleListRes> getArticleList(@PathVariable("category") String category, @ApiIgnore @PageableDefault(size = 12) Pageable pageable) {
        List<Board> articleList = boardService.getArticleListByCategory(category,pageable);
        return ResponseEntity.status(200).body(ArticleListRes.of(200, "success", articleList));
    }

    @GetMapping("/detail/{boardId}")
    @ApiOperation(value = "게시글 상세조회", notes = "선택한 게시글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<ArticleDetailRes> getArticleDetail(@ApiIgnore Authentication authentication,@PathVariable("boardId") Long boardId) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        Long userId = appUserDetails.getUserId();
        Board board = boardService.getBoardById(boardId).get();
        Optional<UserBoardLike> userBoardLike = boardService.getUserBoardLike(boardId, userId);
        // 있으면 삭제 후 return 0
        boolean likeState=userBoardLike.isPresent();

        Long likeCount = boardService.getBoardLikeCount(board);
        return ResponseEntity.status(200).body(ArticleDetailRes.of(200,"success",board,likeState,likeCount));
    }

    @GetMapping("/myboard") // ex) http://localhost:8080/api/board/myboard?page=0
    @ApiOperation(value = "내 게시글 전체조회", notes = "토큰을 이용해 내가 쓴 게시글 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<ArticleListRes> getMyBoardList(@ApiIgnore Authentication authentication, @ApiIgnore @PageableDefault(size = 15) Pageable pageable) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        Long userId = appUserDetails.getUserId();
        List<Board> myBoardList = boardService.getArticleListByUserId(userId,pageable);
        return ResponseEntity.status(200).body(ArticleListRes.of(200, "success", myBoardList));
    }

    @PostMapping
    @ApiOperation(value = "게시글 생성", notes = "토큰을 이용해 유저가 게시글을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> createArticle(@ApiIgnore Authentication authentication, @RequestBody @Validated CreateArticleReq createArticleReq, @ApiIgnore @PageableDefault(size = 12) Pageable pageable) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        User user = appUserDetails.getAppUser();
        Board board = boardService.createArticle(user, createArticleReq);
        List<Board> boardList = boardService.getArticleListByCategory(board.getCategory(),pageable);
        return ResponseEntity.status(200).body(ArticleCreateRes.of(200, "success",boardList,board.getId()));
    }

    @PatchMapping("/detail/{boardId}")
    @ApiOperation(value = "게시글 수정", notes = "토큰을 이용해 유저가 게시글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updateArticle(@ApiIgnore Authentication authentication, @PathVariable Long boardId, @RequestBody Map<Object,Object> objectMap) {
        Long writerId = boardService.getBoardById(boardId).get().getUser().getId();
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        Long userId = appUserDetails.getUserId();
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
    public ResponseEntity<? extends BaseResponseBody> deleteArticle(@ApiIgnore Authentication authentication, @PathVariable Long boardId) {
        Long writerId = boardService.getBoardById(boardId).get().getUser().getId();
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        Long userId = appUserDetails.getUserId();
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
    public ResponseEntity<ArticleLikeRes> likeArticle(@ApiIgnore Authentication authentication, @PathVariable Long boardId) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        Long userId = appUserDetails.getUserId();
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
    public ResponseEntity<? extends BaseResponseBody> createComment(Authentication authentication, @PathVariable Long board_id, @RequestBody CreateCommentReq createCommentReq) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        User user = appUserDetails.getAppUser();
        Optional<Board> board = boardService.getBoardById(board_id);
        boardService.createComment(user, board.get(), createCommentReq);
        List<Comment> commentList = boardService.getComment(board_id);
        return ResponseEntity.status(200).body(GetCommentRes.of(commentList, 200, "댓글작성이 완료되었습니다."));
    }

    @GetMapping("/comment/{board_id}")
    public ResponseEntity<? extends GetCommentRes> getComment(@PathVariable Long board_id) {
        List<Comment> commentList = boardService.getComment(board_id);
        return ResponseEntity.status(200).body(GetCommentRes.of(commentList, 200, "success"));
    }

    @PutMapping("/comment/{board_id}/{comment_id}")
    public ResponseEntity<? extends BaseResponseBody> updateComment(Authentication authentication,@PathVariable Long board_id,@PathVariable Long comment_id, @RequestBody @Validated UpdateCommentReq updateCommentReq) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        User user = appUserDetails.getAppUser();
        Optional<Comment> comment = boardService.getCommentById(comment_id);
        if (comment.get().getUser().getId().equals(user.getId())) {
            boardService.updateComment(comment.get(), updateCommentReq);
            List<Comment> commentList = boardService.getComment(board_id);
            return ResponseEntity.status(200).body(GetCommentRes.of(commentList, 200, "success"));
        }
        throw new IllegalStateException("댓글 작성자가 아닙니다.");
    }

    @DeleteMapping("/comment/{board_id}/{comment_id}")
    public ResponseEntity<? extends BaseResponseBody> deleteComment(Authentication authentication,@PathVariable Long board_id, @PathVariable Long comment_id) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        User user = appUserDetails.getAppUser();
        Optional<Comment> comment = boardService.getCommentById(comment_id);
        if (comment.get().getUser().getId().equals(user.getId())) {
            boardService.deleteComment(comment_id);
            List<Comment> commentList = boardService.getComment(board_id);
            return ResponseEntity.status(200).body(GetCommentRes.of(commentList, 200, "success"));
        }
        throw new IllegalStateException("댓글 작성자가 아닙니다.");
    }
    @PostMapping("/report")
    public ResponseEntity<? extends BaseResponseBody> ReportArticle(Authentication authentication, @RequestParam Long board_id, @RequestBody @Validated ReportArticleReq reportArticleReq) {
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        User user = appUserDetails.getAppUser();
        Optional<Board> board = boardService.getBoardById(board_id);
        boardService.reportArticle(user,board.get(), reportArticleReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
    }

    @GetMapping("/check/{category}")
    public ResponseEntity<CheckArticleRes> CheckArticle(Authentication authentication, @PathVariable String category){
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getDetails();
        Long userId = appUserDetails.getUserId();
        boolean checked = boardService.checkArticleExistence(userId, category);
        return ResponseEntity.status(200).body(CheckArticleRes.of(200, "success", checked));
    }
}
