package com.spider.zhiye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration
@SpringBootConfiguration
@Configuration
@ImportResource(locations={"classpath:applicationContext.xml"})
@SpringBootApplication(scanBasePackages = "com.spider.zhiye")
@EnableJpaRepositories(basePackages = "com.spider.zhiye.jpa.repository")
@EntityScan(basePackages = "com.spider.zhiye.jpa.entity")
public class WorkApplicationTestContext {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WorkApplicationTestContext.class,args);
        Thread.sleep(100000000L);
    }
}
