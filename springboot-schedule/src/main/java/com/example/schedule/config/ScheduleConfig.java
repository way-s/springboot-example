package com.example.schedule.config;

import com.example.schedule.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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
        taskRegistrar.setScheduler(taskExecutor1());
    }

    public void taskExecutor() {
        log.info("taskExecutor：动态任务" + Thread.currentThread().getName() + "\t" + DataUtil.sdf(new Date()));
    }

    /**
     * 调度线程池执行器
     *
     * @return
     */
    @Bean
    public Executor taskExecutor1() {
        return new ScheduledThreadPoolExecutor(10,
                // org.apache.commons.lang3
                new BasicThreadFactory.
                        Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
    }

}
