package com.leetcode.week8;

public class Day6 {
    /**
     * 跳跃游戏，一个中级题比较快速的做出来了
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int target = 0;
        for(int i=0;i<nums.length;i++){
            if(i>target){
                break;
            }
            int a = nums[i]+i;
            if(target<=a){
                target = a;
                if(target>=nums.length){
                    break;
                }
            }
        }
        return target>=nums.length-1;
    }
}
