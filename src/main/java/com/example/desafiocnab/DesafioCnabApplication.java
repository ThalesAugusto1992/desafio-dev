package com.example.desafiocnab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesafioCnabApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioCnabApplication.class, args);
    }

}
