package com.names;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class NamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NamesApplication.class, args);
    }

}