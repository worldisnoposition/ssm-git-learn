package com.learn.websocket;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface WebSocketTestService {
    CompletableFuture chufa(int i) throws InterruptedException, IOException;
}
