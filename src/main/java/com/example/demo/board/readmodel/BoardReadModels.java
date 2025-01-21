package com.example.demo.board.readmodel;

import com.example.demo.board.domain.BoardStatus;
import lombok.Builder;

import java.time.Instant;

public final class BoardReadModels {
    private BoardReadModels() {}

    @Builder
    public record BoardSummary(Long id, String title, BoardStatus status, Instant createdAt) {}

    @Builder
    public record BoardDetail(
            Long id,
            String title,
            String content,
            BoardStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
