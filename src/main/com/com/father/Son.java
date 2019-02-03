package com.father;

public class Son extends Father {
    static{
        System.out.println("子类静态块");
    }
    {
        System.out.println("子类普通块");
    }
    public Son(){
        System.out.println("子类构造");
    }

    public static void main(String[] args) {
        Son son = new Son();
    }
}
