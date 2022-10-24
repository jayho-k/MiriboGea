package com.ssafy.backend.repository;

import com.ssafy.backend.response.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
