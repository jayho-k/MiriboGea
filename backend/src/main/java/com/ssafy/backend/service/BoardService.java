package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Comment;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.request.CreateArticleReq;
import com.ssafy.backend.request.CreateCommentReq;
import com.ssafy.backend.request.UpdateCommentReq;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board createArticle(User user, CreateArticleReq createArticleReq);

    Comment createComment(User user,Board board, CreateCommentReq createCommentReq);
    List<Comment> getComment(Long board_id);
    void updateComment(Comment comment, UpdateCommentReq updateCommentReq);
    void deleteComment(Long comment_id);


    Optional<Board> getBoardById(Long id);

    Optional<Comment> getCommentById(Long id);
}
