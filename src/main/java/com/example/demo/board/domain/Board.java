package com.example.demo.board.domain;

import com.example.demo.common.jpa.support.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor // (access = AccessLevel.PRIVATE)
@NoArgsConstructor // (access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Board extends BaseTimeEntity { // id, createdAt, updatedAt
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private BoardStatus status; // ordinal

    @Builder(
            builderMethodName = "prepareUpdate",
            buildMethodName = "update",
            builderClassName = "BoardUpdateBuilder"
    )
    public void updateBoard(String title, String content) {
        Objects.requireNonNull(title, "업데이트 할 title은 null일 수 없습니다.");
        Objects.requireNonNull(content, "업데이트 할 content는 null일 수 없습니다.");

        if (this.status == null || !this.status.canRead()) {
            throw new IllegalStateException("접근할 수 없는 게시물입니다.");
        }

        this.title = title;
        this.content = content;
    }

    public void softDelete() {
        this.status = BoardStatus.REMOVED;
    }
}
