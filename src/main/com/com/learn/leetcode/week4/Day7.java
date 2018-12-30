package com.learn.leetcode.week4;

public class Day7 {
    /**
     * 感觉我的答案和好答案的思路差不多，开始我也想递归，后来觉得循环完全可以，但怎么这么慢
     * 改了StringBuilder后也差不多，还是不快
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String result = "1";
        for(int i=0;i<n-1;i++){
            int count=1;
            String s = "";
            for(int j=0;j<result.length();j++){
                if(j==result.length()-1){
                    s+=count+""+result.charAt(j);
                }else if((result.charAt(j)!=result.charAt(j+1))){
                    s+=count+""+result.charAt(j);
                    count=1;
                }else{
                    count++;
                }
            }
            result = s;
        }
        return result;
    }
}
