package com.example.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

    private int count;
    @Scheduled(cron = "*/2 * * * * ?")
    private void do11(){
        System.out.println("do count = "+count++);
    }



}
