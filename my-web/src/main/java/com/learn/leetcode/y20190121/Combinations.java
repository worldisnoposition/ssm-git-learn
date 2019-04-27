package com.learn.leetcode.y20190121;

import java.util.LinkedList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList();
        if (k == 0) {
            return result;
        }
        help(result, new LinkedList(), k, 1, n);
        return result;
    }

    private void help(List<List<Integer>> result, LinkedList<Integer> left, int k, int start, int n) {
        if (k == 0) {
            //这步值得我学习
            result.add(new LinkedList(left));
            return;
        }
        while (start + k - n <= 1) {
            left.add(start++);
            help(result, left, k - 1, start, n);
            left.removeLast();
        }
    }
}
