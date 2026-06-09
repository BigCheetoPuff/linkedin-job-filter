package com.cheeto.linkedin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cheeto.linkedin.service.dao")
public class LinkedinJobFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkedinJobFilterApplication.class, args);
    }

}
