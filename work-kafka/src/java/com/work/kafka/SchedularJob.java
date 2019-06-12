package com.work.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedularJob {
    @Autowired
    private KafkaProducer kafkaProducer;

    /**
     * 定时任务
     */

//    @Scheduled(cron = "0/1 * * * * ?")
    private void job(){
        for(int i=0;i<1;i++){
            kafkaProducer.send(i);
        }
    }
}
