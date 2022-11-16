package com.ssafy.backend.service;

import com.ssafy.backend.entity.*;
import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.repository.ReportRepository;
import com.ssafy.backend.repository.UserBoardLikeRepository;
import com.ssafy.backend.repository.CommentRepository;
import com.ssafy.backend.request.CreateArticleReq;
import com.ssafy.backend.request.CreateCommentReq;
import com.ssafy.backend.request.ReportArticleReq;
import com.ssafy.backend.request.UpdateCommentReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    private final UserBoardLikeRepository userBoardLikeRepository;

    private final ReportRepository reportRepository;

    @Override
    public Board createArticle(User user, CreateArticleReq createArticleReq) {
        Board board = new Board();
        board.setUser(user);
        board.setTitle(createArticleReq.getTitle());
        board.setContent(createArticleReq.getContent());
        board.setCategory(createArticleReq.getCategory());
        board.setPicURL(createArticleReq.getPicURL());
        return boardRepository.save(board);
    }

    @Override
    public Board updateArticle(Long boardId, Map<Object, Object> objectMap) {
        Board board = boardRepository.findById(boardId).get();
        objectMap.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(Board.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,board,value);
        });
        return boardRepository.save(board);
    }

    @Override
    public void deleteArticle(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public List<Board> getArticleListByCategory(String category, Pageable pageable) {
        log.debug("category {}",category);
        return boardRepository.findAll();
    }

    @Override
    public List<Board> getArticleListByUserId(Long userId, Pageable pageable) {
        return boardRepository.findAll(pageable).stream().filter(v -> v.getUser().getId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserBoardLike> getUserBoardLike(Long boardId, Long userId) {
        return userBoardLikeRepository.findAll().stream().filter(v -> v.getBoard().getId().equals(boardId) && v.getUser().getId().equals(userId)).findAny();
    }

    @Override
    public void deleteArticleLike(Long userBoardLikeId) {
        userBoardLikeRepository.deleteById(userBoardLikeId);
    }

    @Override
    public UserBoardLike createArticleLike(User user, Board board) {
        UserBoardLike userBoardLike = new UserBoardLike();
        userBoardLike.setUser(user);
        userBoardLike.setBoard(board);
        return userBoardLikeRepository.save(userBoardLike);
    }

    @Override
    public Comment createComment(User user,Board board,CreateCommentReq createCommentReq) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBoard(board);
        comment.setContent(createCommentReq.getContent());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(Comment comment, UpdateCommentReq updateCommentReq) {
        String commentContent = updateCommentReq.getContent();
        LocalDateTime updatedAt = updateCommentReq.getCreatedAt();
        comment.setContent(commentContent);
        comment.setCreatedAt(updatedAt);
        commentRepository.save(comment);
    }
    @Override
    public void deleteComment(Long comment_id) {
        commentRepository.deleteById(comment_id);
    }

    @Override
    public List<Comment> getComment(Long board_id) {
        // board 아이디 찾으면 되는 것
        return commentRepository.findByBoardId(board_id);
    }

    @Override
    public void reportArticle(User user, Board board, ReportArticleReq reportArticleReq) {
        Report report = new Report();
        report.setReporter(user);
        report.setBoard(board);
        report.setContent(reportArticleReq.getContent());
        report.setState("unread");
        reportRepository.save(report);
    }

    @Override
    public boolean checkArticleExistence(Long userId, String category) {
        List<Board> boardList = boardRepository.findByUserId(userId);
        for (Board board : boardList){
            if (board.getCategory().equals(category)){
                return true;
            }
        }
        return false;
    }


    @Override
    public Long getBoardLikeCount(Board board) {
        return userBoardLikeRepository.countByBoard(board);
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
