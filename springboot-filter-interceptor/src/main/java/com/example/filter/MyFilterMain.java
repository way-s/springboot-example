package com.example.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: mxx
 * @Description:
 */
//@ServletComponentScan //扫描WebFilter注解
@SpringBootApplication
public class MyFilterMain {

    public static void main(String[] args) {
        SpringApplication.run(MyFilterMain.class, args);
    }
}
