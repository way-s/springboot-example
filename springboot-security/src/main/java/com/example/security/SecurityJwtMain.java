package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: mxx
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
public class SecurityJwtMain {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtMain.class, args);
    }
}
