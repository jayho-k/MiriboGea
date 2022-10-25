package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserBoardLike;
import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.repository.UserBoardLikeRepository;
import com.ssafy.backend.request.CreateArticleReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final UserBoardLikeRepository userBoardLikeRepository;

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
    public Optional<Board> getArticleById(Long boardId) {
        return boardRepository.findById(boardId);
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
    public List<Board> getArticleListByCategory(String category) {
        return boardRepository.findAll().stream().filter(v -> v.getCategory().equals(category)).collect(Collectors.toList());
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
    public int createComment(Long user_id, Long board_id) {
        try{
            // 저장 => repository
        }catch (Exception e){
            return 2;
        }

        return 0;
    }
    @Override
    public int deleteComment(Long user_id, Long comment_id) {
        return 0;
    }
}
