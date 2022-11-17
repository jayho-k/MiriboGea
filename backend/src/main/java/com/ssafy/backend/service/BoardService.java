package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Comment;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserBoardLike;
import com.ssafy.backend.request.CreateArticleReq;
import com.ssafy.backend.request.CreateCommentReq;
import com.ssafy.backend.request.ReportArticleReq;
import com.ssafy.backend.request.UpdateCommentReq;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Map;

public interface BoardService {

    Board createArticle(User user, CreateArticleReq createArticleReq);

    Board updateArticle(Long boardId, Map<Object, Object> objectMap);

    void deleteArticle(Long boardId);

    List<Board> getArticleListByCategory(String category, Pageable pageable);

    List<Board> getArticleListByUserId(Long userId, Pageable pageable);

    Optional<UserBoardLike> getUserBoardLike(Long boardId, Long userId);

    void deleteArticleLike(Long userBoardLikeId);

    UserBoardLike createArticleLike(User user, Board board);

    Comment createComment(User user,Board board, CreateCommentReq createCommentReq);

    List<Comment> getComment(Long board_id);

    void updateComment(Comment comment, UpdateCommentReq updateCommentReq);

    void deleteComment(Long comment_id);

    Optional<Board> getBoardById(Long id);

    Optional<Comment> getCommentById(Long id);

    void reportArticle(User user, Board board, ReportArticleReq reportArticleReq);

    boolean checkArticleExistence(Long userId, String category);

    Long getBoardLikeCount(Board board);
}
