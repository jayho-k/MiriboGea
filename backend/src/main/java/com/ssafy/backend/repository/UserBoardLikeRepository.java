package com.ssafy.backend.repository;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBoardLikeRepository extends JpaRepository<UserBoardLike, Long> {
    Long countByBoard(Board board);
}
