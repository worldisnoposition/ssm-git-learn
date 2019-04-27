package com.learn.leetcode.week6;

public class Day3 {

    /**
     * 一道hard题，本来一点思路没有，上网找了找答案，看了一会就明白，其实很简单，思路有了，一会就做出来了
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int[] temp = new int[nums.length+1];
        int result = nums.length;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=nums.length&&nums[i]>0){
                temp[nums[i]]=1;
            }
        }
        for(int i=1;i<temp.length;i++){
            if(temp[i]!=1){
                return i;
            }
        }
        return nums.length+1;
    }
}
