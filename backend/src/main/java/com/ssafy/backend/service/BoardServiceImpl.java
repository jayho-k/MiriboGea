package com.ssafy.backend.service;

import com.ssafy.backend.repository.BoardRepository;
import com.ssafy.backend.repository.CommentRepository;
import com.ssafy.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

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
