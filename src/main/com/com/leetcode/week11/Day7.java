package com.leetcode.week11;

public class Day7 {
    /**
     * 动态规划题，直接看的答案了，有了几道题的基础，
     * 看这个答案倒是轻松了很多，一看就懂了，以后这种题要自己去解
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int w1 = word1.length(),w2 = word2.length(),temp;
        int[][] dp = new int[w1+1][w2+1];
        for(int i=0;i<=w1;i++){
            dp[i][0]=i;
        }
        for(int i=0;i<=w2;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<=w1;i++){
            for(int j=1;j<=w2;j++){
                dp[i][j]=Math.min(dp[i-1][j]+1,dp[i][j-1]+1);
                temp=(word1.charAt(i-1)==word2.charAt(j-1)?0:1);
                dp[i][j]=Math.min(dp[i][j],dp[i-1][j-1]+temp);
            }
        }
        return dp[w1][w2];
    }
}
