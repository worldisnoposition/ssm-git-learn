package com.spider.zhiye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
public class WorkApplicationContext {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WorkApplicationContext.class,args);
        Thread.sleep(100000000L);
    }
}
