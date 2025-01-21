package com.example.demo.board.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// enum: 선택지가 있는 구조 (열거 상수들)
//  enum Algorithm { SHA1, SHA256, ... }

// NOTE 이 구현은 초보자에게 어려운 구조입니다. 이 코드를 참고하기보다 샘플 코드의 간략화된 내용을 참고하세요.
public enum BoardStatus {
    PENDING(1, 0, false),
    ACTIVE(2, 0, true),
    SUSPENDED(3, 0, true),
    REMOVED(0, 0, false);

    private static final Set<BoardStatus> READABLE_STATUSES;

    // enum의 static 블록: 클래스가 로드될 때 모든 열거상수가 준비된 다음 실행 (모든 인스턴스가 생성된 후 실행)
    // 일반 클래스의 static 블록: 클래스가 로드될 때 어떤 생성자보다도 먼저 실행 (첫 인스턴스도 생성되기 전 실행)
    static {
        // Enum의 함수:
        //  - (static) values()
        //  - name()
        //  - ordinal(): 고전적인 C언어 스타일의 enum처럼 순서에 따라 값이 생깁니다. 거의 활용하지 않습니다.
        for (var item : BoardStatus.values()) {
            System.out.println(STR."name: \{item.name()}, ordinal: \{item.ordinal()}");
        }

        var set = Arrays.stream(BoardStatus.values())
                .map(BoardStatus::code)
                .collect(Collectors.toSet());

        assert set.size() == values().length : "코드는 중복되어선 안 됩니다.";

        READABLE_STATUSES = Arrays.stream(BoardStatus.values())
                .filter(BoardStatus::canRead)
                .collect(Collectors.toSet());
    }

    private static final int CLASSIFYING_MAX_VALUE = (~0) >>> 16; // 65535 (0000_0000 0000_0000 1111_1111 1111_1111)
    private static final int ADDITIONAL_MAX_VALUE = (~0) >>> 16; // 65535 (0000_0000 0000_0000 1111_1111 1111_1111)

    private final int code;
    private final boolean canRead;

    BoardStatus(int classifyingValue, int additionalValue, boolean canRead) {
        assert classifyingValue <= CLASSIFYING_MAX_VALUE
                : STR."상위 분류 코드는 \{CLASSIFYING_MAX_VALUE} 이하여야 합니다.";
        assert additionalValue <= ADDITIONAL_MAX_VALUE
                : STR."세부 분류 코드는 \{ADDITIONAL_MAX_VALUE} 이하여야 합니다.";

        // CCCC_CCCC CCCC_CCCC 0000_0000 0000_0000 (4바이트)
        //  C: 상위 분류 코드
        //  0: 세부 분류 코드
        this.code = (classifyingValue << 16) | additionalValue;
        this.canRead = canRead;
    }

    public static Set<BoardStatus> readableStatuses() {
        return READABLE_STATUSES;
    }

    public int code() {
        return code;
    }

    public boolean canRead() {
        return canRead;
    }
}
