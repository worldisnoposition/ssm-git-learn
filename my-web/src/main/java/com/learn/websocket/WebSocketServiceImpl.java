package com.learn.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.concurrent.CompletableFuture;

@Component
//@Slf4j
public class WebSocketServiceImpl implements WebSocketTestService{
    @PostConstruct
    public void init(){
        System.out.println("init 成功");
        System.out.println(this);
        System.out.println(AopUtils.isAopProxy(this));
        System.out.println(AopUtils.isCglibProxy(this));
        System.out.println(AopUtils.isJdkDynamicProxy(this));
    }
    @Async
    public CompletableFuture chufa(int i) throws InterruptedException, IOException {
        System.out.println(AopUtils.isAopProxy(this));
        System.out.println(AopUtils.isCglibProxy(this));
        System.out.println(AopUtils.isJdkDynamicProxy(this));
        try {
//        WebSocketTestImpl ws = new WebSocketTestImpl();
            System.out.println(this);
            Object o = AopContext.currentProxy();
            System.out.println(o);
            WebSocketTestService webSocketServiceImpl = (WebSocketTestService) AopContext.currentProxy();
            System.out.println(webSocketServiceImpl);
            Thread.sleep(1000L);
            WebSocketTestImpl ws = WebSocketTestImpl.clients.values().iterator().next();
            JSONObject jo = new JSONObject();
            jo.put("message", "这是后台返回的消息！--->" + i);
            jo.put("To", "haha");
            ws.onMessage(jo.toString());
        }catch (Exception e){
//            log.error("报错了e:{}",e);
        }
        return null;
    }
}
