package com.leetcode.week8;

import java.util.ArrayList;
import java.util.List;

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
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length==0){
            return new ArrayList();
        }
        int[][] to = {{1,0},{0,1},{-1,0},{0,-1}};
        int curser = 0,t=0;
        List<Integer> result = new ArrayList();
        int width = matrix[0].length,height = matrix.length,total = width*height;
        for(int i=0,j=0;;){
            // System.out.println(result);
            // System.out.println(i+"-"+j+"-"+curser+"-"+width+"-"+height);
            result.add(matrix[j][i]);
            if(result.size()==total){
                break;
            }
            if(curser==0&&i==width-1){
                curser=1;
                width--;
            }else if(curser==1&&j==height-1){
                curser=2;
                height--;
            }else if(curser==2&&i==t){
                curser=3;
                // width--;
                t++;
            }else if(curser==3&&j==t){
                curser=0;
                // height--;
            }
            i+=to[curser][0];
            j+=to[curser][1];
        }
        return result;
    }
}
