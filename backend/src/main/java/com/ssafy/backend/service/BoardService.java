package com.ssafy.backend.service;

public interface BoardService {

    int createComment(Long user_id, Long board_id);
    int deleteComment(Long user_id, Long comment_id);
}
