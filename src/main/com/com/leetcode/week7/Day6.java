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

    /**
     * 跳跃数组，我的答案非常慢侥幸通过了一次测试
     * 实际上第一次抵达终点就可以判定通过了，而我这个做法做的太多次了
     * @param num
     * @return
     */
    public int jump(int[] num) {
        if(num.length<=1){
            return 0;
        }
        int[] result = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i]; j++) {
                int t = i+j+1;
                if(t>=num.length){
                    break;
                }
                if (result[t] == 0 || result[t] > result[i]) {
                    result[t] = result[i] + 1;
                }
            }
        }
        return result[num.length - 1];
    }

    /**
     *
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int start = 0;
        int end = 0;

        int maxIdx = 0;
        int step = 1;

        while (start <= end) {
            for (int i = start; i <= end; ++i) {
                maxIdx = Math.max(maxIdx, nums[i] + i);
            }
            if (maxIdx >= nums.length - 1) {
                return step;
            }
            step++;
            start = end + 1;
            end = maxIdx;
        }

        return 0;
    }
}
