package com.ssafy.backend.service;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.request.CreateArticleReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

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
