package com.example.demo.board.usecase;

import com.example.demo.board.readmodel.BoardReadModels.BoardSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardReadAllUseCase {
    // import 조심!!! (임포트에 혼란을 주는 awt는 자바 GUI 개발 라이브러리입니다. JPA와 대체로 무관합니다.)
    Page<BoardSummary> findAllBy(Pageable pageable);
}
