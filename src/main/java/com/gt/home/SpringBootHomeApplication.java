package com.gt.home;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gt.home.dao")
public class SpringBootHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHomeApplication.class, args);
    }
}
