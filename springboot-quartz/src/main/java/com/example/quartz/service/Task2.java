package com.example.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: mxx
 * @Description: 具体任务
 */
@Slf4j
@DisallowConcurrentExecution // 保证job的唯一性（单例）
public class Task2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        log.info("Task2  线程id：{}, 执行时间：{}", Thread.currentThread().getId(), date);
    }
}
