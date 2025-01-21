package com.example.demo.board.usecase;

import com.example.demo.board.readmodel.BoardReadModels.BoardDetail;

public interface BoardReadOneUseCase {
    BoardDetail findById(Long id);
}
