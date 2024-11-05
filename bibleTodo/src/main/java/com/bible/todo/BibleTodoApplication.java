package com.bible.todo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.bible.todo.domain.bible.mapper", "com.bible.todo.domain.user.mapper"})
public class BibleTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibleTodoApplication.class, args);
    }

}
