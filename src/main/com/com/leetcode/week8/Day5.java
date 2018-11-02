package com.leetcode.week8;

public class Day5 {
    int count=0;

    /**
     * 和上一道题很像，稍改了一下就好了
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        dfs(n, 0, new int[n]);
        return count;
    }

    public void dfs(int n, int row, int[] col) {
        if (row >= n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            col[row] = i;
            if (isValid(row, col)) {
                dfs(n, row + 1, col);
            }
        }
    }

    public boolean isValid(int row, int[] col) {
        for (int i = 0; i < row; i++) {
            if (col[row] == col[i] || Math.abs(col[row] - col[i]) == row - i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 一道简单题超越了100%的人，耶
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int total = nums[0],result = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]>=0){
                if(total>=0){
                    total +=nums[i];
                }else{
                    total = nums[i];
                }
            }else{
                if(total+nums[i]>=0){
                    total+=nums[i];
                }else{
                    total = nums[i];
                }
            }
            if(total>result){
                result = total;
            }
        }
        return result;
    }
}
