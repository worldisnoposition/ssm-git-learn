package com.work.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

//@EnableAutoConfiguration
@SpringBootConfiguration
@Configuration
//@ImportResource(locations={"classpath:application.properties"})
@SpringBootApplication(scanBasePackages = "com.work.kafka")
public class KafkaProducerApplicationContext {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(KafkaProducerApplicationContext.class,args);
        Thread.sleep(100000000L);
    }
}
