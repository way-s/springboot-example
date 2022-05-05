package com.example.schedule.config;

import com.example.schedule.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    /**
     * 创建任务
     * 实现定时任务的两种方式：
     * 1.使用注解 @Scheduled
     * 2.实现 SchedulingConfigurer接口
     *
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addFixedDelayTask(this::taskExecutor, 2000);
    }

    public void taskExecutor() {
        log.info("taskExecutor：动态任务" + Thread.currentThread().getName() + DataUtil.sdf(new Date()));
    }

}
