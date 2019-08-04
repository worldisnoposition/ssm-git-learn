package com.auto.service;

import com.auto.client.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public void service() {
        System.out.println("TestServiceImpl is here");
    }
}
