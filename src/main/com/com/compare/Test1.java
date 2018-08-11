package com.compare;

import java.util.Collection;

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
