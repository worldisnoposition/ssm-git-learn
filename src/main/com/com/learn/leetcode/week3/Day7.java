package com.learn.leetcode.week3;

public class Day7 {
    public int search(int[] nums, int target) {
        int result = -1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                result = i;
                break;
            }
        }
        return result;
    }
}