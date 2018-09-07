package com.leetcode;

public class Day7 {
    /**
     * 我的n*n时间复杂度的垃圾算法，我怎么就想不到呢
     * @param height
     * @return
     */
     public int maxArea(int[] height) {
         int result = 0;
         for(int i=0;i<height.length;i++){
             for(int j=i+1;j<height.length;j++){
                 int temp = (j-i)*(height[i]>height[j]?height[j]:height[i]);
                 if(temp>result){
                     result = temp;
                 }
             }
         }
         return result;
     }

    /**
     * 别人的牛逼算法，真是精巧，叹服，学习
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int i=0;
        int j=height.length-1;
        int res = 0;
        while(i<j){
            res = Math.max(res,(j-i)*(height[i]>height[j]?height[j]:height[i]));
            if(height[i]>height[j]) j--;
            else i++;
        }
        return res;
    }
}
