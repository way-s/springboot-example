package com.example.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author m
 * @Title: EhcacheApplication
 * @Description:
 * @Date: 2022/4/29
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
public class EhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhcacheApplication.class, args);
    }
}
