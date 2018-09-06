package com.leetcode;

public class Day2 {
    public static void main(String[] args) {
    }

    /**
     * temp.indexOf(cs[i])这一步时间复杂度n,如果用hashmap做时间复杂度为常数，效率高很多
     * 截取字符串中距离最长的两个相同字符
     * @param s
     * @return
     */
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
