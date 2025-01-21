package com.example.demo.board.controller.dto;

import com.example.demo.board.domain.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public final class BoardCommandDtos {
    private BoardCommandDtos() {} // final class + private 생성자만 => 유틸리티클래스처럼 상속 X, 인스턴스 생성 X

    @Builder
    public record BoardCreateRequest(
            @NotBlank(message = "제목을 입력하십시오.") // null, empty "", blank " "
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            @Size(max = 50, message = "제목은 최대 50글자입니다.")
            String title,

            @NotBlank(message = "본문을 입력하십시오.")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content
    ) {}

    @Builder
    public record BoardUpdateRequest(
            @NotBlank(message = "제목을 입력하십시오.") // null, empty "", blank " "
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            @Size(max = 50, message = "제목은 최대 50글자입니다.")
            String title,

            @NotBlank(message = "본문을 입력하십시오.")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content
    ) {}

    @Builder
    public record BoardCreateResponse(Board board) {}

    @Builder
    public record BoardUpdateResponse(Board board) {}
}
