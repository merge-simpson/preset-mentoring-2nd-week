package com.example.demo.board.controller;

import com.example.demo.board.controller.dto.BoardCommandDtos.BoardCreateRequest;
import com.example.demo.board.controller.dto.BoardCommandDtos.BoardCreateResponse;
import com.example.demo.board.controller.dto.BoardCommandDtos.BoardUpdateResponse;
import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.BoardStatus;
import com.example.demo.board.usecase.BoardCreateUseCase;
import com.example.demo.board.usecase.BoardDeleteUseCase;
import com.example.demo.board.usecase.BoardUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "boards")
@Validated
public class BoardCommandApi {

    private final BoardCreateUseCase boardCreateUseCase;
    private final BoardUpdateUseCase boardUpdateUseCase;
    private final BoardDeleteUseCase boardDeleteUseCase;

    /* HTTP Methods 의미
     *  GET: 조회
     *  POST: 삽입
     *  PUT: 업데이트 (업데이트, 삽입 둘 다지만... 업데이트로만.)
     *  (PATCH): 특정 부분만 골라서 거기만 업데이트 (비밀번호 변경 등)
     *  DELETE: 삭제
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 CREATED
    public BoardCreateResponse create(
            // @Valid -> 유효성 애노테이션이 여기 바로 있진 않더라도
            //  이 클래스 안쪽에 들어가면 있으니까
            //  안쪽까지 가서 봐라 하는 지시
            @RequestBody @Valid BoardCreateRequest dto
    ) {
        // DTO -> Entity
        Board entity = Board.builder()
                .title(dto.title())
                .content(dto.content())
                .status(BoardStatus.ACTIVE)
                .build();

        // Save entity
        Board savedEntity = boardCreateUseCase.create(entity);

        // return
        return BoardCreateResponse.builder()
                .board(savedEntity)
                .build();
    }

    @PutMapping("/{id}")
    public BoardUpdateResponse update(
            @PathVariable("id") long id,
            @RequestBody @Valid BoardCreateRequest dto
    ) {
        var updatedBoard = boardUpdateUseCase.update(id, dto.title(), dto.content());

        return BoardUpdateResponse.builder()
                .board(updatedBoard)
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        boardDeleteUseCase.delete(id);
    }
}
