package com.serializable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class UserWithSerialization implements Serializable {
    private static final long serialVersionUID = -1;

    public UserWithSerialization(String name, Integer age, Map hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    private String name;
    private Integer age;

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

    private Map hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
