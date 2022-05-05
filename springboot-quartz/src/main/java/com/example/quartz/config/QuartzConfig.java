package com.example.quartz.config;

import com.example.quartz.service.Task1;
import com.example.quartz.service.Task2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: mxx
 * @Description:
 */
@Configuration
public class QuartzConfig {
    /**
     * 创建任务
     * 没有实现 Quartz持久化配置
     * <p>
     * Job - 定时任务内容是什么。
     * Trigger - 在什么时间上执行job。
     * Scheduler - 维护定时任务环境，并让触发器生效。
     *
     * @return
     */
    @Bean
    public JobDetail taskJobDetail1() {
        return JobBuilder.newJob(Task1.class)
                .withIdentity("Task1")
                .storeDurably(true)
                .build();
    }

    @Bean
    public JobDetail taskJobDetail2() {
        return JobBuilder.newJob(Task2.class)
                .withIdentity("Task2")
                .storeDurably(true)
                .build();
    }

    /**
     * 任务触发器
     *
     * @return
     */
    @Bean
    public Trigger taskTrigger1() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/2 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(taskJobDetail1())
                .withIdentity("task1")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger taskTrigger2() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/2 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(taskJobDetail2())
                .withIdentity("task2")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
