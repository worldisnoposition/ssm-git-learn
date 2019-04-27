package com.learn.leetcode.week3;

public class Day2 {
    /**
     * 一道简单题还折腾半天
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length<=1){
            return nums.length;
        }
        int j=1;
        for(int i = 1 ;i<nums.length;i++){
            if(nums[i]!=nums[i-1]){
                nums[j]=nums[i];
                j++;
            }
        }
        return j;
    }

    /**
     * 这道题和上题超级像，一气呵成，直接从头到尾的写完了，直接run通过，直接submit通过
     * 运气爆棚，超过了百分之99.98；
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[j]=nums[i];
                j++;
            }
        }
        return j;
    }

    /**
     * 是我误解了题意了吗，这是中级题的难度？
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        Long result = (dividend+0L)/divisor;
        if(result>Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return result.intValue();
    }
}
