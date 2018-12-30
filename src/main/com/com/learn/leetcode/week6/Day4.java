package com.learn.leetcode.week6;

public class Day4 {
    /**
     * 水桶题，hard难度，废了九牛二虎之力才跑通，效率还贼低
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height.length<3){
            return 0;
        }
        int left=0,right=0,result=0,righti=-1;
        for(int i=1;i<height.length-1;i++){
            // System.out.println("======");
            if((left==0||right==0)||i>righti||height[i]>left){
                left=0;
                right=0;
                for(int j=0;j<height.length;j++){
                    // System.out.println(i+"-"+j+"-"+left+"-"+right+"-"+result);
                    // System.out.println((j<i)+"-"+(height[j]>height[i])+"-"+(height[j]>left));
                    if(j<i&&height[j]>height[i]&&height[j]>left){
                        left=height[j];
                        // lefti=j;
                    }
                    if(j>i&&height[j]>height[i]&&height[j]>right){
                        right=height[j];
                        righti=j;
                    }
                }
                if(left!=0&&right!=0){
                    int temp = left>right?right-height[i]:left-height[i];
                    if(temp>0){
                        result+=temp;
                    }
                }
            }else{
                if(left!=0&&right!=0){
                    int temp = left>right?right-height[i]:left-height[i];
                    if(temp>0){
                        result+=temp;
                    }
                }
            }
            // System.out.println(left+"-"+right+"-"+result);
        }
        return result;
    }

    /**
     * 正确答案从两边往中间夹逼，上一个题其实也是这样的，哎，我的思路跑偏太多了
     * @param height
     * @return
     */
    public int trapBIAOZHUN(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > maxLeft) {
                    maxLeft = height[left++];
                } else {
                    res += maxLeft - height[left++];
                }
            } else {
                if (height[right] > maxRight) {
                    maxRight = height[right--];
                } else {
                    res +=maxRight - height[right--];
                }
            }
        }
        return res;
    }
}
