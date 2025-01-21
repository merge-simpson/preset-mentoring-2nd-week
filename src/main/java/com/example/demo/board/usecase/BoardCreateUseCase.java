package com.example.demo.board.usecase;

import com.example.demo.board.domain.Board;

// UseCase: 서비스 인터페이스를 기능 단위로 나눠 놓은 것
public interface BoardCreateUseCase {
    Board create(Board board);
}
