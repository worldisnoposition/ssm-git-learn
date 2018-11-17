package com.compare;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Test2 extends AbstractComparable {

    public Test2(){
        super();
    }
    @Override
    public void update(Collection toUpdate) {
        System.out.println("2.update");
    }

    @Override
    public void save(Collection toSave) {

    }

}
