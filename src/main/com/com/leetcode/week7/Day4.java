package com.leetcode.week7;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Day4 {

    @Test
    public void a(){
        System.out.println(isMatch("aab","c*a*b"));
    }

    /**
     * 我用的递归方法应该是对的，但是超时了，正确答案是动态规划算法，我还需要看看
     * 还额外学到一个小细节，把很多个连续的*优化成1个*。
     * 动态规划算法明天再搞吧
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        StringBuilder pp = new StringBuilder();
        if(p.length()>1){
            for(int i=1;i<p.length();i++){
                if(p.charAt(i)!='*'||p.charAt(i-1)!='*'){
                    pp.append(p.charAt(i));
                }
            }
        }else if(p.length()==1){
            return isMatch(s,p,0,0);
        }
        return isMatch(s,pp.toString(),0,0);
    }
    public boolean isMatch(String s,String p,int i,int j){
        if(i==s.length()&&j==p.length()){
            return true;
        }
        if(i>=s.length()){
            for(;j<p.length();j++){
                if('*'!=p.charAt(j)){
                    return false;
                }
            }
            return true;
        }
        if(i>=s.length()||j>=p.length()){
            return false;
        }
        if(s.charAt(i)==p.charAt(j)||'?'==p.charAt(j)){
            return isMatch(s,p,++i,++j);
        }else if('*'==p.charAt(j)){
            for(;i<s.length();){
                if(isMatch(s,p,i,j+1)||isMatch(s,p,++i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 大数相乘，思路基本正确，细节有很多不仔细啊
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        //char数组从低位开始相加 减
        int[] n1 = new int[num1.length()];
        int[] n2 = new int[num2.length()];
        int t = n1.length+n2.length;
        int[] result = new int[t];
        for(int i=0;i<num1.length();i++){
            n1[i]=num1.charAt(i)-'0';
        }
        for(int i=0;i<n2.length;i++){
            n2[i]=num2.charAt(i)-'0';
        }
        for(int i=n1.length-1;i>=0;i--){
            for(int j=n2.length-1;j>=0;j--){
                sum(result,i+j+1,n1[i]*n2[j]);
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean begin = false;
        for(int i=0;i<result.length;i++){
            if(begin){
                sb.append(result[i]+"");
            }else if(!begin&&result[i]!=0){
                sb.append(result[i]+"");
                begin=true;
            }
        }
        if(!begin){
            return "0";
        }
        return sb.toString();
    }
    public void sum(int[] result,int now,int toSum){
        toSum = result[now]+toSum;
        if(toSum>9){
            result[now]=toSum%10;
            sum(result,now-1,toSum/10);
        }else{
            result[now]=toSum;
        }
    }
    /**
     *
     * 一道中级题，思路和答案也基本一致，但差在了result里的list和map里的list是同一个对象，我的写法for循环了两次效率稍低
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap();
        List<List<String>> result = new ArrayList();
        for(int i=0;i<strs.length;i++){
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);
            if(map.get(key)!=null){
                map.get(key).add(strs[i]);
            }else{
                List<String> list = new ArrayList();
                list.add(strs[i]);
                map.put(key,list);
                result.add(list);
            }
        }
        return result;
    }
}
