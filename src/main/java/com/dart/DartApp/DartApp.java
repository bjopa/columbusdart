package com.dart.DartApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DartApp {

    public static void main(String[] args) {
        SpringApplication.run(DartApp.class, args);
    }

}
