package com.create.compare;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ComparableBean
@Component
public class TestBean2 {
    @Key
    @Value("这就是id")
    private String id;
    @Comparable
    private String hello;
    @Comparable
    private Integer testInt;
    public TestBean2(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
