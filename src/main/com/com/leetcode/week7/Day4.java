package com.leetcode.week7;

import java.util.*;

public class Day4 {
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
