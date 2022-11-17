package com.ssafy.backend.repository;

import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findAll(Pageable pageable);
    List<Board> findByUserId(Long id);
}
