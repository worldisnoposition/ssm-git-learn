package com.compare;

import org.springframework.stereotype.Component;

@ComparableBean
@Component
public class TestBean2 {
    @Key
    private String id;
    @Comparable
    private String hello;
    @Comparable
    private Integer testInt;
    public TestBean2(){

    }
    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
    }
}