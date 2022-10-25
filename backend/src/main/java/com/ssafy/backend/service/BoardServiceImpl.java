package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.Comment;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.repository.CommentRepository;
import com.ssafy.backend.request.CreateArticleReq;
import com.ssafy.backend.request.CreateCommentReq;
import com.ssafy.backend.request.UpdateCommentReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public Board createArticle(User user, CreateArticleReq createArticleReq) {
        Board board = new Board();
        board.setUser(user);
        board.setTitle(createArticleReq.getTitle());
        board.setContent(createArticleReq.getContent());
        board.setCategory(createArticleReq.getCategory());
        board.setPicURL(createArticleReq.getPicURL());
        board.setCreatedAt(createArticleReq.getCreatedAt());
        return boardRepository.save(board);
    }

    @Override
    public Comment createComment(User user,Board board,CreateCommentReq createCommentReq) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBoard(board);
        comment.setContent(createCommentReq.getContent());
        comment.setCreatedAt(createCommentReq.getCreatedAt());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment updateComment(Comment comment, UpdateCommentReq updateCommentReq) {
        String commentContent = updateCommentReq.getContent();
        LocalDateTime updatedAt = updateCommentReq.getCreatedAt();
        comment.setContent(commentContent);
        comment.setCreatedAt(updatedAt);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComment(Long board_id) {
        // board 아이디 찾으면 되는 것
        List<Comment> allOfComments = commentRepository.findAll();
        return allOfComments.stream()
                .filter(comment -> comment.getBoard()
                .getId().equals(board_id)).collect(Collectors.toList());
    }

    @Override
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }


}
