package com.create.compare;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
//@ComparableBean
public class Test1<T> extends AbstractComparable<T> {
    public Test1(){
        super();
    }
    @Override
    public void update(Collection toUpdate) {
        System.out.println("2.update");
    }

    @Override
    public void save(Collection toSave) {
        System.out.println("save");
    }

}
