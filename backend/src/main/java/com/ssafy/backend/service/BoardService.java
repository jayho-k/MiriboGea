package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.CreateArticleReq;

public interface BoardService {

    Board createArticle(User user, CreateArticleReq createArticleReq);

    int createComment(Long user_id, Long board_id);
    int deleteComment(Long user_id, Long comment_id);
}
