package com.serc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.serc.mapper")
public class SercApplication {

    public static void main(String[] args) {
        SpringApplication.run(SercApplication.class, args);
    }

}
