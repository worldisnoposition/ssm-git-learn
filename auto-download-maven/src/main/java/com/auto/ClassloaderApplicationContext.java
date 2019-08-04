package com.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author 肖贺博
 */
@SpringBootConfiguration
@Configuration
//@ImportResource(locations={"classpath:applicationContext.xml"})
@SpringBootApplication(scanBasePackages = "com.auto")
public class ClassloaderApplicationContext {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ClassloaderApplicationContext.class,args);
        Thread.sleep(100000000L);
    }
}
