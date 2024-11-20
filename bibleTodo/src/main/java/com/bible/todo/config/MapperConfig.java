package com.bible.todo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.bible.todo.domain.bible.mapper", "com.bible.todo.domain.user.mapper", "com.bible.todo.domain.join.mapper", "com.bible.todo.domain.login.mapper", "com.bible.todo.domain.comments.mapper"})
public class MapperConfig {

}
