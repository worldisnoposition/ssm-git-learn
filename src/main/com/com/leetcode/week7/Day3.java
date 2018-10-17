package com.leetcode.week7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {
    /**
     * 我的思路简单暴力但不最优
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int a = matrix.length;
        int[][] result = new int[a][a];
        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                result[j][a-1-i] = matrix[i][j];
            }
        }
        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                matrix[i][j] = result[i][j];
            }
        }
    }

    /**
     * 别人的答案 首先以从对角线为轴翻转，然后再以x轴中线上下翻转即可得到结果
     * @param matrix
     */
    public void rotateRENJIA(int[][] matrix) {
        int n = matrix.length;
        //step 1: transpose
        for (int i = 0; i < n; i++){
            for (int j = 0; j < i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        //step 2: flip horizontally
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n/2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }

    /**
     * week6 day2做的题稍微改了一下，但还是那个问题，传值应该用list一直传过去，而不是我这样每次遍历一次数组
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if(nums==null||nums.length==0){
            return new ArrayList();
        }else if(nums.length==1){
            List<Integer> temp = new ArrayList();
            temp.add(nums[0]);
            result.add(temp);
        }else{
            Arrays.sort(nums);
            for(int i=0;i<nums.length;i++){
                if(i!=0&&nums[i]==nums[i-1]){
                    continue;
                }
                int[] next = remove(nums, i);
                List<List<Integer>> nextList = permuteUnique(next);
                for(int j=0;j<nextList.size();j++){
                    List<Integer> temp = new ArrayList();
                    temp.add(nums[i]);
                    temp.addAll(nextList.get(j));
                    result.add(temp);
                }
            }
        }
        return result;
    }
    public int[] remove(int[] nums,int toremove){
        if(nums!=null&&nums.length!=0){
            int[] result = new int[nums.length-1];
            for(int i=0,j=0;i<nums.length;i++){
                if(i==toremove){
                    continue;
                }
                result[j++]=nums[i];
            }
            return result;
        }
        return null;
    }
}
