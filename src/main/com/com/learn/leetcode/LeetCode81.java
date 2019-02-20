package com.learn.leetcode;

class LeetCode81 {
    public boolean search(int[] nums, int target) {
        if(nums.length == 0){
            return false;
        }
        int left = 0,right = nums.length - 1;
        while(left <= right){
            int middle = (left + right) / 2;
            if (target == nums[middle]) {
                return true; 
            }else if(nums[right] > nums[middle]){
                if(target > nums[middle] && target <= nums[right]){
                    left = middle + 1;
                }else{
                    right = middle - 1;
                }
            } else if (nums[middle] > nums[right]){
                if(target >= nums[left] && target < nums[middle]){
                    right = middle - 1;
                }else{
                    left = middle + 1;
                }
            } else{
                right--;
            }
        }
        return false;
    }
}