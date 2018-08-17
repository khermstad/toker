package com.khermstad.toker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@ComponentScan
public class TokerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TokerApplication.class, args);
    }
}
