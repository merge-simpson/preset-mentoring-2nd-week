package com.example.demo.board.service;

import com.example.demo.board.domain.BoardStatus;
import com.example.demo.board.readmodel.BoardReadModels.BoardDetail;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.board.readmodel.BoardReadModels.BoardSummary;
import com.example.demo.board.usecase.BoardReadAllUseCase;
import com.example.demo.board.usecase.BoardReadOneUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BoardQueryService implements BoardReadOneUseCase, BoardReadAllUseCase {

    private final BoardRepository boardRepository;

    @Override
    public BoardDetail findById(Long id) {
        return boardRepository.findBoardById(id)
                .filter((board) -> board.status() != null && board.status().canRead())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물입니다."));
    }

    @Override
    public Page<BoardSummary> findAllBy(Pageable pageable) {
        // 조회 가능한 상태 목록
        Set<BoardStatus> readableStatuses = BoardStatus.readableStatuses();

        // 조회 1: 데이터
        List<BoardSummary> boards = boardRepository.findAllByStatusInOrderByCreatedAtDesc(readableStatuses, pageable);

        // 페이지 객체를 반환 (+ 조회 2: 전체 개수 조회. 지금은 그냥 카운트를 사용합니다.)
        return PageableExecutionUtils.getPage(boards, pageable, boardRepository::count);
    }
}
