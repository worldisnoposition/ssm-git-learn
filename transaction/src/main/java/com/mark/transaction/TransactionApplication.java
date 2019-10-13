package com.mark.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 肖贺博
 */
@SpringBootConfiguration
@EnableScheduling
//@ImportResource(locations={"classpath:applicationContext.xml"})
@SpringBootApplication(scanBasePackages = "com.mark")
public class TransactionApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(TransactionApplication.class,args);
        Thread.sleep(100000000L);
    }
}
