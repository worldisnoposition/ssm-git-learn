package com.compare;

import java.util.Collection;

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
