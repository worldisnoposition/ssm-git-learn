package com.work.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//@EnableAutoConfiguration
@SpringBootConfiguration
@Configuration
//@ImportResource(locations={"classpath:application.properties"})
@SpringBootApplication(scanBasePackages = "com.work.kafka")
public class KafkaConsumerApplicationContext {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(KafkaConsumerApplicationContext.class,args);
        Thread.sleep(100000000L);
    }
}
