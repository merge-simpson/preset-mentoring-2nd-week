package com.example.demo.board.controller;

import com.example.demo.board.controller.dto.BoardQueryDtos.BoardReadOneResponse;
import com.example.demo.board.readmodel.BoardReadModels.BoardDetail;
import com.example.demo.board.readmodel.BoardReadModels.BoardSummary;
import com.example.demo.board.usecase.BoardReadAllUseCase;
import com.example.demo.board.usecase.BoardReadOneUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardQueryApi {

    private final BoardReadOneUseCase boardReadOneUseCase;
    private final BoardReadAllUseCase boardReadAllUseCase;

    @GetMapping("/{id}")
    public BoardReadOneResponse readOne(@PathVariable long id) {
        BoardDetail board = boardReadOneUseCase.findById(id);

        return BoardReadOneResponse.builder()
                .board(board)
                .build();
    }

    @GetMapping
    public Page<BoardSummary> readAll(
            @PageableDefault(page = 1, size = 5) Pageable pageable
    ) {
        return boardReadAllUseCase.findAllBy(pageable);
    }
}
