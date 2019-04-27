package com.learn.leetcode;

/**
 * 这道题我没做出来，超的其他人的答案，顺便学习了动态规划算法，收益很大
 */
public class Day8 {
    /**
     * 递归算法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 字符串为空的判断
        if (s == null && p == null)
            return true;
        if (s == null && p != null || s != null && p == null)
            return false;
        // 字符串不为空
        int n = s.length(), m = p.length();
        // p的长度为0时的判断
        if (n == 0 && m == 0)
            return true;
        if (m == 0 && n != 0)
            return false;
        // m>0的判断
        if (p.charAt(m - 1) == '.' && n > 0) {
            return isMatch(s.substring(0, n - 1), p.substring(0, m - 1));// 匹配任意字符：将s去掉一个以及p去掉一个
        } else if (p.charAt(m - 1) == '*') {// 对于*号的判断:精髓所在
            if (m > 1) {// 当m>1则进行后面的判断
                if (n > 0)// 当s字符串长度大于0则进行下面的判断
                {
                    if (p.charAt(m - 2) == '.') {// 匹配任意字符：将s去掉一个以及p暂时不变
                        if (isMatch(s.substring(0, n - 1), p))
                            return true;// 如果返回true则直接返回，否则执行将p字符串去掉后面两个字符
                    } else if (p.charAt(m - 2) == '*') {
                        return false;// 连续两个的*直接退出
                    } else {
                        if (p.charAt(m - 2) == s.charAt(n - 1)) {
                            if (isMatch(s.substring(0, n - 1), p))
                                return true;
                            // 如果返回true则直接返回，否则执行将p字符串去掉后面两个字符
                        }
                    }
                }
                return isMatch(s, p.substring(0, m - 2));// 上面返回为FALSE和s长度为0时用的递归
            }
        } else {
            if (n > 0) {
                if (p.charAt(m - 1) == s.charAt(n - 1))
                    return isMatch(s.substring(0, n - 1), p.substring(0, m - 1));
                // 此去为简单判断：当最后一个字符相等就执行减字符递归
            }
        }
        return false;// 所有条件都不满足，则直接返回FALSE
    }

    public static void main(String[] args) {
        Day8 d = new Day8();
        d.isMatchDp("hahaha","ha*ha");
    }
    /**
     * 动态规划算法，还没看懂这道题的解法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchDp(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        boolean[][] dp = new boolean[lp + 1][ls + 1];
        dp[0][0] = true;

        for (int i = 1; i <= lp; i++) {
            for (int j = 0; j <= ls; j++) {
                if (p.charAt(i - 1) == '.') {
                    dp[i][j] = j > 0 && dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    if (i < 2) {
                        return false;
                    }
                    if (j == 0) {
                        dp[i][j] = dp[i - 2][j];
                        continue;
                    }
                    if (p.charAt(i - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i - 2][j] || dp[i][j - 1];
                    } else {
                        if (p.charAt(i - 2) == s.charAt(j - 1)) {
                            dp[i][j] = dp[i - 1][j] || dp[i - 2][j] || dp[i][j - 1];
                        } else {
                            dp[i][j] = dp[i - 2][j];
                        }
                    }
                } else {
                    dp[i][j] = j > 0 && dp[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }
        return dp[lp][ls];
    }
}
