package com.example.schedule.service;


import com.example.schedule.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Component
public class ScheduleService {

    @Scheduled(fixedDelayString = "2000")
    public void scheduleTask1() {
        log.info("scheduleTask1 定时任务：" + Thread.currentThread().getId() + "\t" + DataUtil.sdf(new Date()));
    }

    /**
     * cron [秒] [分] [小时] [日] [月] [周] [年]
     * 每隔2秒执行一次
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void scheduleTask2() {
        log.info("scheduleTask2 定时任务：" + Thread.currentThread().getName() + "\t" + DataUtil.sdf(new Date()));
    }

}
