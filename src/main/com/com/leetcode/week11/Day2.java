package com.leetcode.week11;

import java.util.ArrayList;
import java.util.List;

public class Day2 {
    /**
     * 全排列的题目
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList();
        int mod = 1;
        for(int i=1;i<=n;i++){
            list.add(i);
            mod *= i;
        }
        k=k-1;
        String result = "";
        for(int i=0;i<n;i++){
            mod = mod/(n-i);
            int index = k/mod;
            k=k%mod;
            result = result + list.get(index);
            list.remove(index);
        }
        return result;
    }

    /**
     * 矩阵中不重复的路径共有多少
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] t = new int[m];
        for(int i=0;i<m;i++){
            t[i] = 1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                t[j]=t[j]+t[j-1];
            }
        }
        return t[m-1];
    }

    /**
     * 和上一题类似，区别在于中间会有过不去的坎
     * @param a
     * @return
     */
    public int uniquePathsWithObstacles(int[][] a) {
        int[][] temp = new int[a.length][a[0].length];
        for(int i=0;i<temp.length;i++){
            if(a[i][0]==1){
                break;
            }
            temp[i][0] = 1;
        }
        for(int i=0;i<temp[0].length;i++){
            if(a[0][i]==1){
                break;
            }
            temp[0][i] = 1;
        }
        for(int i=1;i<a.length;i++){
            for(int j=1;j<a[0].length;j++){
                if(a[i][j]==1){
                    temp[i][j]=0;
                }else{
                    temp[i][j]=temp[i-1][j]+temp[i][j-1];
                }
            }
        }
        return temp[a.length-1][a[0].length-1];
    }
}
