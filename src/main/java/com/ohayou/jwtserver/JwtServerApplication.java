package com.ohayou.jwtserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.ohayou.jwtserver.mapper"})
public class JwtServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtServerApplication.class, args);
    }

}
