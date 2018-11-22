package com.leetcode.week11;

public class Day4 {
    /**
     * 还是比较简单的开方题目，但是疏忽了超过int最大值的问题
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int l=x/2,h=l+1,t=l/2;
        long l2 = 1l*l*l,h2=1l*h*h;
        while(!(l2<x&&h2>x)){
            // System.out.print(l2+".."+h2);
            if(l2>x){
                l=l-t;
            }else{
                l=l+t;
            }
            t=t/2>0?t/2:1;
            h=l+1;
            l2=1l*l*l;
            h2=1l*h*h;
            if(l2==x){
                return l;
            }
            if(h2==x){
                return h;
            }
        }
        return l;
    }
}
