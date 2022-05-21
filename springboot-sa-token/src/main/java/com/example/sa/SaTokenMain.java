package com.example.sa;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: mxx
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
public class SaTokenMain {

    public static void main(String[] args) {
        SpringApplication.run(SaTokenMain.class, args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }
}
