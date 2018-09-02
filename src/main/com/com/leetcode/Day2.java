package com.leetcode;

public class Day2 {
        public int lengthOfLongestSubstring(String s) {
            char[] cs = s.toCharArray();
            String temp = "";
            int now;
            int result = 0;
            for(int i=0;i<cs.length;i++){
                if((now=temp.indexOf(cs[i]))==-1){
                    temp+=cs[i];
                }else{
                    if(temp.length()>result){
                        result = temp.length();
                    }
                    temp = temp.substring(now+1);
                    temp +=cs[i];
                }
            }
            if(temp.length()>result){
                result = temp.length();
            }
            return result;
        }
}
