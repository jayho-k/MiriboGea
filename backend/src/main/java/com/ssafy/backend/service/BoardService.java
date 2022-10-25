package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Comment;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserBoardLike;
import com.ssafy.backend.request.CreateArticleReq;
import com.ssafy.backend.request.CreateCommentReq;
import com.ssafy.backend.request.UpdateCommentReq;

import java.util.List;
import java.util.Optional;
import java.util.Map;

public interface BoardService {

    Board createArticle(User user, CreateArticleReq createArticleReq);

    Board updateArticle(Long boardId, Map<Object, Object> objectMap);

    void deleteArticle(Long boardId);

    List<Board> getArticleListByCategory(String category);

    Optional<UserBoardLike> getUserBoardLike(Long boardId, Long userId);

    void deleteArticleLike(Long userBoardLikeId);

    UserBoardLike createArticleLike(User user, Board board);

    Comment createComment(User user,Board board, CreateCommentReq createCommentReq);

    List<Comment> getComment(Long board_id);

    void updateComment(Comment comment, UpdateCommentReq updateCommentReq);

    void deleteComment(Long comment_id);

    Optional<Board> getBoardById(Long id);

    Optional<Comment> getCommentById(Long id);
}
