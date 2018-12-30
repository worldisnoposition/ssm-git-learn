package com.learn.leetcode.week11;

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

    /**
     * 最快答案用的是除法，这思路真是牛逼了
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = x;
        while (true) {

            int mid = left + (right - left)/2;

            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

    /**
     * 又是一个简单题，和斐波那契数列差不多
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n<=2)
            return n;
        int[] t = new int[n];
        t[0]=1;
        t[1]=2;
        for(int i=2;i<n;i++){
            t[i]=t[i-1]+t[i-2];
        }
        return t[n-1];
    }
}
