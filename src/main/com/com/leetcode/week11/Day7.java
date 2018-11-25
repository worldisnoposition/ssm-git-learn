package com.leetcode.week11;

public class Day7 {
    /**
     * 动态规划题，直接看的答案了，有了几道题的基础，
     * 看这个答案倒是轻松了很多，一看就懂了，以后这种题要自己去解
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int w1 = word1.length(), w2 = word2.length(), temp;
        int[][] dp = new int[w1 + 1][w2 + 1];
        for (int i = 0; i <= w1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= w2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= w1; i++) {
            for (int j = 1; j <= w2; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                temp = (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + temp);
            }
        }
        return dp[w1][w2];
    }

    /**
     * 判断是不是数字，用到了状态机，这个时很高效的做法，
     * 还有的答案是正则，卧槽，代码量倒是少，但是调试和性能都是问题
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for (int i = 0; i < s.length(); i++) {
            //当前值为数字
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
                //遇到小数点
            } else if (s.charAt(i) == '.') {
                //已经遇到小数点或是e，则出错
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
                //遇到e
            } else if (s.charAt(i) == 'e') {
                //已经遇到e或是尚未遇到数字
                if (eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
                //遇到正负号，只能在首位或是e后面
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
                //遇到其它符号一定是错的
            } else {
                return false;
            }
        }
        //是否遇到小数点或是e均不重要
        return numberSeen && numberAfterE;
    }

}
