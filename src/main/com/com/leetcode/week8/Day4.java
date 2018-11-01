package com.leetcode.week8;

import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        Day4 d = new Day4();
        System.out.println(d.solveNQueens(4));
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList();
        if (n <= 0) {
            return res;
        }
        dfs(n, res, 0, new int[n]);
        return res;
    }

    public void dfs(int n, List<List<String>> res, int row, int[] col) {
        if (row >= n) {
            List<String> one = new ArrayList();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (col[i] == j) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                one.add(sb.toString());
            }
            res.add(one);
            return;
        }
        for (int i = 0; i < n; i++) {
            col[row] = i;
            if (isValid(row, col)) {
                dfs(n, res, row + 1, col);
            }
        }
        System.out.println("第几次");
    }

    public boolean isValid(int row, int[] col) {
        for (int i = 0; i < row; i++) {
            if (col[row] == col[i] || Math.abs(col[row] - col[i]) == row - i) {
                return false;
            }
        }
        return true;
    }
}
