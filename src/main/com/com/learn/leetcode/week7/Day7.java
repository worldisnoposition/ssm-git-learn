package com.learn.leetcode.week7;

public class Day7 {
    public static void main(String[] args) {
        Day7 d =new Day7();
        System.out.println(d.isMatch2("ho","**ho"));
    }
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        if(p.length()>0&&p.charAt(0)=='*'){
            dp[0][1]=true;
        }
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i-1][j-1]||dp[i-1][j]||dp[i][j-1];
                }else{
                    dp[i][j] = dp[i-1][j-1]&&(p.charAt(j-1)=='?'||p.charAt(j-1)==s.charAt(i-1));
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 他这个去重这里的操作我还是没搞懂
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p) {
        String tp = "";

        //处理p中多余的*
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                tp += '*';
                while (i < p.length() && p.charAt(i) == '*') i++;
            }
            if (i < p.length()) {
                tp += p.charAt(i);
            }
        }
        p = tp;

        boolean[][] f = new boolean[s.length() + 1][p.length() + 1];
        f[0][0] = true;

        // 注意，当p以*开头时
        if (p.length() > 0 && p.charAt(0) == '*') {
            f[0][1] = true;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i - 1][j - 1] || f[i - 1][j] || f[i][j - 1];
                } else {
                    f[i][j] = f[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                }
            }

        }

        return f[s.length()][p.length()];
    }

    /**
     * 这是排名最优的答案
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int si = 0, pi = 0, match = 0, star = -1;

        while(si < s.length()) {
            if(pi < p.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
                si++;
                pi++;
            }
            else if(pi < p.length() && p.charAt(pi) == '*') {
                star = pi;
                match = si;
                pi++;
            }
            else if(star != -1) {
                pi = star + 1;
                match++;
                si = match;
            }
            else return false;
        }

        while(pi < p.length() && p.charAt(pi) == '*') pi++;

        return pi == p.length();

    }
}
