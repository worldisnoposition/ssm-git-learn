package com.learn.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/webSocket/{username}")
public class WebSocketTestImpl implements WebSocketTestService{
    private static int onlineCount = 0;
    public static Map<String, WebSocketTestImpl> clients = new ConcurrentHashMap<String, WebSocketTestImpl>();
    private Session session;
    private String username;
    @Resource
    private  WebSocketServiceImpl webSocketService;

    @PostConstruct
    public void init(){
        System.out.println("init WebSocketTestImpl  成功");
    }
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException, InterruptedException {

        this.username = username;
        this.session = session;

        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");
//        WebSocketTestImpl webSocketTestImpl = (WebSocketTestImpl)AopContext.currentProxy();
        for(int i=0;i<10;i++){
            webSocketService.chufa(i);
        }
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000L);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t.start();
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {

        JSONObject jsonTo = JSONObject.parseObject(message);
        String mes = (String) jsonTo.get("message");

        if (!jsonTo.get("To").equals("All")){
            sendMessageTo(mes, jsonTo.get("To").toString());
        }else{
            sendMessageAll("给所有人");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebSocketTestImpl item : clients.values()) {
            if (item.username.equals(To) )
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketTestImpl item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketTestImpl.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketTestImpl.onlineCount--;
    }

    public static synchronized Map<String, WebSocketTestImpl> getClients() {
        return clients;
    }

    @Override
    public CompletableFuture chufa(int i) throws InterruptedException, IOException {
        return null;
    }
}
