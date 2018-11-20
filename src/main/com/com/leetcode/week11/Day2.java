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
}
