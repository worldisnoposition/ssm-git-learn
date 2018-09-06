package com.leetcode;

public class Day6 {

    /**
     * 这题出的真恶心，莫名其妙的题目，中级题
     */
    class Solution {
        public int myAtoi(String str) {
            //0:48 9:57 +:43 -:45
            // System.out.print(' '+0+"");
            str=str.trim();
            char[] c = str.toCharArray();
            boolean start = false;
            int flag = 1;
            boolean wordFlag = false;
            Long result = 0L;
            for(int i=0;i<c.length;i++){
                if(start){
                    if(c[i]>=48&&c[i]<=57){
                        result=result*10+(c[i]-48);
                        if(result*flag<Integer.MIN_VALUE){
                            return Integer.MIN_VALUE;
                        }else if(result*flag>Integer.MAX_VALUE){
                            return Integer.MAX_VALUE;
                        }
                    }else{
                        break;
                    }
                }else if(c[i]==45){
                    flag = -1;
                    start = true;
                }else if(c[i]==43){
                    start = true;
                }else if(c[i]>=48&&c[i]<=57){
                    if(wordFlag){
                        return 0;
                    }
                    start = true;
                    result = c[i]-48L;
                }else{
                    wordFlag = true;
                }
            }
            return result.intValue()*flag;
        }
    }

    /**
     * 初级题，居然写了好长时间，除了好多错，就是反转个数字，但是边界考虑的不周到
     * @param x
     * @return
     */
    public int reverse(int x) {
        Long result=0L;
        while(x!=0){
            result=(x%10)+result*10;
            x/=10;
        }
        if(result<Integer.MIN_VALUE||result>Integer.MAX_VALUE){
            return 0;
        }
        return x<0?-result.intValue():result.intValue();
    }

    /**
     * 又做了一道初级题
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int y=0,z=x;
        while(x!=0){
            y=y*10+x%10;
            x/=10;
        }
        return y==z;
    }
}
