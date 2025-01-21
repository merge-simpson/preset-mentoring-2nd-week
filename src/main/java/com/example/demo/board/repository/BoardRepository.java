package com.example.demo.board.repository;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.BoardStatus;
import com.example.demo.board.readmodel.BoardReadModels.BoardDetail;
import com.example.demo.board.readmodel.BoardReadModels.BoardSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // Board save(Board entity); -> 자동으로 만들어진다.
    // Optional<Board> findById(Long id);
    // Page<Board> findAll(Pageable pageable);
    // void deleteById(Long id);

    // 추가
    // SELECT
    //  find + (특정 키워드들) + 아무말 + By(= where) + 컬럼 등등...
    //  List<BoardSummary> findBoardsByStatusIn(Set<BoardStatus> status, Pageable pageable);
    //  Optional<BoardDetail> findBoardById(Long id);

    Optional<BoardDetail> findBoardById(Long id);
    List<BoardSummary> findAllByStatusInOrderByCreatedAtDesc(Set<BoardStatus> status, Pageable pageable);
}
