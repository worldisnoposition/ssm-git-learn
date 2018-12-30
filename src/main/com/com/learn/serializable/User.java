package com.learn.serializable;

import java.util.Map;


public class User {
    private String name;
    private Integer age;
    private Map hobby;

    public User(String name, Integer age, Map hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map getHobby() {
        return hobby;
    }

    public void setHobby(Map hobby) {
        this.hobby = hobby;
    }
}
