package com.learn.leetcode.week11;

import java.util.LinkedList;

public class Day5 {
    /**
     * 文件路径，栈的题目，很久以前我还写过一个四则运算的栈，比这个复杂多了，嘿嘿
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] ts = path.split("/");
        LinkedList<String> list = new LinkedList<String>();
        for(String t:ts){
            if(t.equals("")){
                if(list.size()!=0){
                    list.removeLast();
                }
            }else if(t.equals(".")||t.equals("")){
                continue;
            }else{
                list.add(t);
            }
        }
        if(list.size()==0){
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for(Object s:list){
            sb.append("/").append((String)s);
        }
        return sb.toString();
    }
}
