package com.example.demo.board.controller.dto;

import com.example.demo.board.readmodel.BoardReadModels.BoardDetail;
import lombok.Builder;

public final class BoardQueryDtos {
    private BoardQueryDtos() {}

    @Builder
    public record BoardReadOneResponse(BoardDetail board) {}
}
