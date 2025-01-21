package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.board.usecase.BoardCreateUseCase;
import com.example.demo.board.usecase.BoardDeleteUseCase;
import com.example.demo.board.usecase.BoardUpdateUseCase;
import org.springframework.stereotype.Service;

@Service // 빈 등록
public class BoardCommandService // implements Create, Update, Delete
        implements BoardCreateUseCase,
        BoardUpdateUseCase,
        BoardDeleteUseCase {
    // Ctrl + I
    private final BoardRepository boardRepository;

    // 이 생성자 대신: @RequiredArgsConstructor above 클래스 (final, non-null 필드)
    public BoardCommandService(BoardRepository boardRepository/* 빈 오는 자리 */) {
        this.boardRepository = boardRepository; // 파라미터로 받은 빈을 -> 필드에 옮겨 담기
    }

    @Override
    public Board create(Board board) {
        // [1] 레퍼지터리에게 넘기기
        //  board: ID가 없는 엔티티
        //  savedEntity: ID가 생성된 엔티티 (저장도 완료된 엔티티)
        var savedEntity = boardRepository.save(board);

        // 결과 반환
        return savedEntity;
    }

    @Override
    public Board update(Long id, String title, String content) {
        // 조회
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물입니다."));

        // 업데이트
        board.prepareUpdate()
                .title(title)
                .content(content)
                .update();

        // 저장
        return boardRepository.save(board);
    }

    @Override
    public void delete(Long id) {
        // 조회
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물입니다."));

        // 업데이트 (소프트 딜리트 예시)
        board.softDelete();

        // 저장
        boardRepository.save(board);
    }
}
