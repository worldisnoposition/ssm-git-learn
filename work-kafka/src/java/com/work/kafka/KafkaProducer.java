package com.work.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Async
    public void send(int i) {

        String message = "消息" + i + "：" + UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("app_log", message);
        future.addCallback(o -> {
            System.out.println("send-消息发送成功：" + message);
            System.out.println(o);
        }, throwable -> System.out.println("消息发送失败：" + message));
    }

}