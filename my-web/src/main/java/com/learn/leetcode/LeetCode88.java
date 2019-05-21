package com.learn.leetcode;

public class LeetCode88 {
    /**
     * 合并2个数组的
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=nums1.length-1;i>=0;i--,n--){
            if(n-1>=0){
                nums1[i]=nums2[n-1];
            }else{
                break;
            }
        }
        for(int i=0;i<nums1.length-1;i++){
            for(int j=0;j<nums1.length-1-i;j++){
                if(nums1[j]>nums1[j+1]){
                    n=nums1[j];
                    nums1[j]=nums1[j+1];
                    nums1[j+1]=n;
                }
            }
        }
    }
}
