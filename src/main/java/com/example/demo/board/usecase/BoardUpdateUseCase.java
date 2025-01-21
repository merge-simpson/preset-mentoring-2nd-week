package com.example.demo.board.usecase;

import com.example.demo.board.domain.Board;

public interface BoardUpdateUseCase {
    Board update(Long id, String title, String content);
}
