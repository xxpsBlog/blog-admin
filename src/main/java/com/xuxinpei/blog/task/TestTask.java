package com.xuxinpei.blog.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestTask extends BaseTask {
    @Scheduled(cron = "0 0 0 */1 * *")
    public void run() {
        super.run();
    }

    public void doTask() {
    }
}