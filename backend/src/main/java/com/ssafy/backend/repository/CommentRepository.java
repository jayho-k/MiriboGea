package com.ssafy.backend.repository;
;
import com.ssafy.backend.entity.Comment;
import com.ssafy.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
//    Optional<User> findUserById(Long comment_id);
}
