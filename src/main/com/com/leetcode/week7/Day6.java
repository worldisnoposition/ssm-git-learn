package com.leetcode.week7;

public class Day6 {
    /**
     * 求指数，我的算法，在处理负数时会更慢
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(n==Integer.MIN_VALUE){
            double temp = myPow(x,n/2);
            return temp*temp;
        }
        if(n<0){
            x = 1/x;
            n = -n;
        }
        if(n==0){
            return 1;
        }
        if(n==1){
            return x;
        }
        double temp = myPow(x,n/2);
        return n%2==0?temp*temp:temp*temp*x;
    }

    /**
     * 正确答案，很巧妙的避开了我的问题
     * @param x
     * @param n
     * @return
     */
    public double myPow1(double x, int n) {
        if(n == 0)
            return 1;
        double temp = myPow(x,n/2);

        if(n%2==0)
            return temp*temp;
        else
        if(n>0)
            return x*temp*temp;  // for n>0 , it will start building from here
        else
            return temp*temp/x;  // for n<0 it will start building from here
    }
}
