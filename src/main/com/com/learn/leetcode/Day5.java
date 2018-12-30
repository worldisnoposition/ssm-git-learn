package com.learn.leetcode;

public class Day5 {
    /**
     * 之字形字符串转换，我这写法够恶心的了，但起码自己写的，测试时头痛医头，脚痛医脚的结果
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }
        String result = "";
        int b=numRows-1;

        for(int i=0;i<numRows;i++){
            int a = i;
            char c;
            while(a<s.length()&&a>=0){
                if(a%(b)==0){
                    result += s.charAt(a);
                    a+=(numRows-1)*2;
                }else{
                    result+=s.charAt(a);
                    a+=(b-i)*2;
                    if(a>=s.length()){
                        break;
                    }
                    result += s.charAt(a);
                    a+=i*2;
                }
            }
        }
        return result;
    }
}
