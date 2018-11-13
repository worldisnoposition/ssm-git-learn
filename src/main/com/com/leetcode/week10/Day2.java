package com.leetcode.week10;

public class Day2 {
    public int lengthOfLastWord(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        String[] ss = s.split(" ");
        if (ss == null || ss.length == 0) {
            return 0;
        }
        return ss[ss.length - 1].length();
    }
}
