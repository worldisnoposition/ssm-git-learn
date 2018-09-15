package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Day14 {
    /**
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character,Character> temp = new HashMap();
        temp.put('(',')');
        temp.put('{','}');
        temp.put('[',']');
        LinkedList<Character> a = new LinkedList();
        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(a.size()>0&&c.equals(temp.get(a.getLast()))){
                a.pollLast();
            }else{
                a.add(c);
            }
        }
        return a.size()==0;
    }
}
