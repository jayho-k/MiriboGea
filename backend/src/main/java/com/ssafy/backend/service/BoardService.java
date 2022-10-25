package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserBoardLike;
import com.ssafy.backend.request.CreateArticleReq;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardService {

    Board createArticle(User user, CreateArticleReq createArticleReq);

    Optional<Board> getArticleById(Long boardId);

    Board updateArticle(Long boardId, Map<Object, Object> objectMap);

    void deleteArticle(Long boardId);

    List<Board> getArticleListByCategory(String category);

    Optional<UserBoardLike> getUserBoardLike(Long boardId, Long userId);

    void deleteArticleLike(Long userBoardLikeId);

    UserBoardLike createArticleLike(User user, Board board);

    int createComment(Long user_id, Long board_id);
    int deleteComment(Long user_id, Long comment_id);
}
