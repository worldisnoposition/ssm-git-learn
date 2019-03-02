package com.test.log;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;  
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;  
import ch.qos.logback.core.rolling.RollingFileAppender;  
//import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;  
import com.alibaba.fastjson.JSON;  
import com.alibaba.fastjson.JSONArray;  
import com.alibaba.fastjson.JSONObject;  
import org.slf4j.LoggerFactory;  
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoggerBuilder {  
  
    private final Map<String,Logger> container = new HashMap<>();
  
    public Logger getLogger(String name) {
        Logger logger = container.get(name);  
        if(logger != null) {  
            return logger;  
        }  
        synchronized (LoggerBuilder.class) {  
            logger = container.get(name);  
            if(logger != null) {  
                return logger;  
            }  
            logger = build(name);  
            container.put(name,logger);  
        }  
        return logger;  
    }  
  
    private Logger build(String name) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger("FILE-" + name);  
        logger.setAdditive(false);
        RollingFileAppender appender = new RollingFileAppender();  
        appender.setContext(context);  
        appender.setName("FILE-" + name);  
        appender.setFile(OptionHelper.substVars("F://log/web-log-" + name + ".log",context));
        appender.setAppend(true);  
        appender.setPrudent(false);
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        String fp = OptionHelper.substVars("${LOG_HOME}/web-log-" + name + ".log.%d{yyyy-MM-dd}.%i",context);

//        policy.setMaxFileSize(FileSize.valueOf("128MB"));
        policy.setFileNamePattern(fp);
        policy.setMaxHistory(15);
//        policy.setTotalSizeCap(FileSize.valueOf("32GB"));
        policy.setParent(appender);
        policy.setContext(context);
        policy.start();
//
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd/HH:mm:ss.SSS}|%X{localIp}|[%t] %-5level %logger{50} %line - %m%n");
        encoder.start();
//
        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();  
        logger.addAppender(appender);  
        return logger;  
    }  
}  