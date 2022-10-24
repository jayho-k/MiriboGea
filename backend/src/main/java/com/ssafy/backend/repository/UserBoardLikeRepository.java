package com.ssafy.backend.repository;

import com.ssafy.backend.entity.UserBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBoardLikeRepository extends JpaRepository<UserBoardLike, Long> {
}
