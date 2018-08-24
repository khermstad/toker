package com.khermstad.toker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TokerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TokerApplication.class, args);
    }
}
