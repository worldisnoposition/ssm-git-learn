package com.leetcode.week10;

public class Day7 {
    /**
     * 就是个矩阵转圈，我的答案超过了100%，哈哈
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int length = n*n;
        int[][] to = {{1,0},{0,1},{-1,0},{0,-1}};
        int index = 0;
        int r = 0;
        int[][] result = new int[n][n];
        // n=n-1;
        int count=0;
        boolean flag = true;
        for(int i=-1,j=0;r<length;count++){
            if(count>=n){
                if(index == 3){
                    index=0;
                }else{
                    index++;
                }
                // System.out.println(index+"-"+i+"-"+j+"-"+count);
                count=0;
                if(flag){
                    n--;
                }
                flag = !flag;
            }
            i += to[index][0];
            j += to[index][1];
            result[j][i] = ++r;
        }
        return result;
    }
}
