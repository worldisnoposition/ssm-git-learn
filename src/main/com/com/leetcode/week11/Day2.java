package com.leetcode.week11;

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
}
