package com.leetcode;

public class Day4 {
    /**
     * 这是别人的，我自己没做出来。看着学习下
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int mid = (m + n) / 2;
        if((m + n) % 2 == 0) {
            return (findKth(nums1, 0, nums2, 0, mid) + findKth(nums1, 0, nums2, 0, mid+1)) / 2.0;
        } else {
            return findKth(nums1, 0, nums2, 0, mid + 1);
        }
    }

    private int findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if(start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        if(start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if(k == 1) return Math.min(nums1[start1], nums2[start2]);

        int num1 = (start1 + k/2 - 1 < nums1.length) ? nums1[start1 + k/2 - 1] : Integer.MAX_VALUE;
        int num2 = (start2 + k/2 - 1 < nums2.length) ? nums2[start2 + k/2 - 1] : Integer.MAX_VALUE;
        if(num1 < num2) {
            return findKth(nums1, start1 + k/2, nums2, start2, k - k/2);
        } else {
            return findKth(nums1, start1, nums2, start2 + k/2, k - k/2);
        }
    }
//     boolean isOdd=false;
//     int[] NUMS1,NUMS2;
//     public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//         if(nums1.length<nums2.length){
//             this.NUMS1=nums1;
//             this.NUMS2=nums2;
//         }else{
//             this.NUMS1=nums2;
//             this.NUMS2=nums1;
//         }
//         if((nums1.length+nums2.length)%2!=0){
//             isOdd = true;
//         }
//         if(NUMS1.length==0){
//             return (NUMS2[NUMS2.length/2]+NUMS2[(NUMS2.length-1)/2]+0.0d)/2;
//         }
//         return digui(0,NUMS1.length-1,0,NUMS2.length-1);
//     }
//     private double digui(int a1,int a2,int b1,int b2){
//         System.out.println(a1+"-"+a2+"-"+b1+"-"+b2);
//         if(NUMS2[b1]>NUMS1[a2]){
//             int a,b;
//             a=a1+b1;
//             b=NUMS1.length-a2+NUMS2.length-b2-2;
//             if(a>b){

//             }else if(a<b){

//             }
//             if((b2-b1)==(a2-a1)&&!isOdd){
//                 return (NUMS1[a2]+NUMS2[b1])/2.0;
//             }
//             return isOdd?NUMS2[(b1+b2)/2]:(NUMS2[(b1+b2)/2]+NUMS2[(b1+b2-1)/2])/2.0;
//         }else if(NUMS2[b2]<NUMS1[a1]){
//             int a,b;
//             a=a1+b1;
//             b=NUMS1.length-a2+NUMS2.length-b2-2;
//             if((b2-b1)==(a2-a1)&&!isOdd){
//                 return (NUMS1[a1]+NUMS2[b2])/2.0;
//             }
//             return isOdd?NUMS2[(b1+b2+1)/2]:(NUMS2[(b1+b2+2)/2]+NUMS2[(b1+b2+1)/2])/2.0;
//         }
//         if(b2-b1<=1){
//             int a,b;
//             a=a1+b1;
//             b=NUMS1.length-a2+NUMS2.length-b2-2;
//             if(isOdd){
//                 if(a>b){
//                     return Double.max(NUMS1[a1],NUMS2[b1]);
//                 }else if(a<b){
//                     return Double.min(NUMS1[a2],NUMS2[b2]);
//                 }
//                 if(a1==a2){
//                     if(NUMS2[b1]>NUMS1[a1]){
//                         return NUMS2[b1];
//                     }else if(NUMS2[b2]<NUMS1[a2]){
//                         return NUMS2[b2];
//                     }else{
//                         return NUMS1[a1];
//                     }
//                 }
//             }else{
//                 if(a!=b&&a1==a2){
//                     if(NUMS2[b1]>NUMS1[a1]){
//                         return (a>b?(NUMS2[b1]+NUMS1[a1]):(NUMS2[b1]+NUMS2[b2]))/2.0;
//                     }else if(NUMS2[b2]<NUMS1[a2]){
//                         return (a>b?(NUMS2[b1]+NUMS2[b2]):(NUMS1[a1]+NUMS2[b2]))/2.0;
//                     }
//                     return (a>b?(NUMS1[a1]+NUMS2[b1]):(NUMS1[a1]+NUMS2[b2]))/2.0;
//                 }
//             }
//             return (Double.max(NUMS1[a1],NUMS2[b1])+Double.min(NUMS1[a2],NUMS2[b2]))/2;
//         }else{
//             int temp1=NUMS1[(a2+a1-1)/2];
//             int temp2=NUMS2[(b2+b1-1)/2];
//             if(temp1>temp2){
//                 return digui(a1,(a2+a1+1)/2,(b2+b1)/2,b2);
//             }
//             else{
//                 return digui((a2+a1)/2,a2,b1,(b2+b1+1)/2);
//             }
//         }
    // }
}