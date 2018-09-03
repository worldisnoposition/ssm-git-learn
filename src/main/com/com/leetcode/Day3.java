package com.leetcode;

public class Day3 {
//    public static void main(String[] args) {
//        System.out.println(a());
//    }
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int l1,l2;
//        if((l1 = nums1.length)> (l2 = nums2.length))
//            return findMedianSortedArrays(nums2,nums1);
//        if(l2<=2){
//           if(l1==1){
//               return nums1[0]>nums2[0]?nums1[0]:nums2[0]+0.0d;
//           }else{
//               return (0.0d+nums1[0]>nums2[0]?nums1[0]:nums2[0]+nums1[1]<nums2[1]?nums1[1]:nums2[1])/2;
//           }
//        }else{
//            int temp1=nums1[(l1-1)/2];
//            int temp2=nums2[(l2-1)/2];
//            if(temp1>temp2){
//                 return digui(a1,temp1,temp2,b2);
//             }
//             else{
//                 return digui(temp1,a2,b1,temp2);
//             }
//        }
//
//    }
//    int[] jiequshuzu(int[] a,int start,int end){
//        int[] result = new int[end-start+1];
//        System.arraycopy(a,start,result,0,end-start+1);
//        return result;
//    }
    /**
     * 找出字符串中最长的回文数。我的方法是从最大的字符串开始找，很慢，优秀答案都是从当前节点往两边扩展，比我的快很多。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s.length()==1){
            return s;
        }
        char[] chars = s.toCharArray();
        String result="";
        for(int i=0;i<s.length();i++){
            for(int j=s.length()-1;j>i;j--){
                int length = j-i+1;
                if(ishuiwen(chars,i,j,length)){
                    if(j-i+1>result.length()){
                        result = s.substring(i,j+1);
                    }
                    if(j==s.length()-1){
                        return result;
                    }
                }
            }
        }
        if(result.length()==0&&s.length()>1){
            return chars[0]+"";
        }
        return result;
    }

    boolean ishuiwen(char[] chars,int i,int j,int length){
        for(int k=i;k<i+(length/2);k++){
            if(chars[k]!=chars[j+i-k]){
                return false;
            }
        }
        return true;
    }
}
