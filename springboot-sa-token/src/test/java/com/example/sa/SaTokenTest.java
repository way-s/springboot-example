package com.example.sa;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@SpringBootTest
public class SaTokenTest {
    /**
     * 踢人下线
     */
    @Test
    public void testLoginOut() {
        StpUtil.logout(10001);
        log.info("10001 下线");
    }

}
