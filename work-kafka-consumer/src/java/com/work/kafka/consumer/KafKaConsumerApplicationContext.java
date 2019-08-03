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
@ImportResource(locations={"classpath:applicationContext.xml"})
@SpringBootApplication(scanBasePackages = "com.spider.zhiye")
//@EnableJpaRepositories(basePackages = "com.spider.zhiye.jpa.repository")
@EntityScan(basePackages = "com.spider.zhiye.jpa.entity")
public class KafKaConsumerApplicationContext {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(KafKaConsumerApplicationContext.class,args);
        Thread.sleep(100000000L);
    }
}
