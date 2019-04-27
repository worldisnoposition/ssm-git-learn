package com.father;

public class Father {
    static{
        System.out.println("父类静态块");
    }
    {
        System.out.println("父类普通块");
    }
    public Father(){
        System.out.println("父类构造");
    }

}
