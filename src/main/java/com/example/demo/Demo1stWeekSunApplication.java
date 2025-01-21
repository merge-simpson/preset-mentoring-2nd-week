package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class Demo1stWeekSunApplication {

    public static void main(String[] args) throws ClassNotFoundException {
        // enum의 static 블록이 실행되는 시점: 모든 열거상수가 준비된 다음. (일반 클래스와 시점 다름.)
        //  그것을 확인해 보려고 클래스 로드로 테스트해 봅니다: (따라치지 않아도 됩니다.)
//        Class.forName("com.example.demo.board.domain.BoardStatus");

        SpringApplication.run(Demo1stWeekSunApplication.class, args);
    }

}
