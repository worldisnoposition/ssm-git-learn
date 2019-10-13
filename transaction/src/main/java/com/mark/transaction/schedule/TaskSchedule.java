package com.mark.transaction.schedule;

import com.mark.transaction.service.TargetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class TaskSchedule {

    @PostConstruct
    public void init(){
        log.info("init TaskSchedule");
    }

    @Autowired
    private TargetService targetService;

    @Scheduled(cron = "0/5 * * * * *")
    public void transactionTest(){
        log.info("into task");
        targetService.targetMethod("hello,i am being in");
    }
}
