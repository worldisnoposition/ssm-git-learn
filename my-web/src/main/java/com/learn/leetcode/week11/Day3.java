package com.learn.leetcode.week11;

public class Day3 {
    /**
     * 也是最小路径类的题，比较简单
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        for(int i=1;i<grid[0].length;i++){
            grid[0][i]=grid[0][i]+grid[0][i-1];
        }
        for(int i=1;i<grid.length;i++){
            grid[i][0] = grid[i][0]+grid[i-1][0];
        }
        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid[0].length;j++){
                grid[i][j] = (grid[i-1][j]>grid[i][j-1]?grid[i][j-1]:grid[i-1][j])+grid[i][j];
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }

    /**
     * 数组，加一，也很简单的题
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int next = 0;
        for(int i = digits.length-1;i>=0;i--){
            if(i==digits.length-1){
                digits[i]++;
            }else{
                digits[i]+=next;
            }
            if(digits[i]==10){
                next = 1;
                digits[i]=0;
            }else{
                next= 0;
            }
        }
        if(next == 1){
            int[] result = new int[digits.length+1];
            result[0]=1;
            for(int i=1;i<result.length;i++){
                result[i]=digits[i-1];
            }
            return result;
        }
        return digits;
    }

    /**
     * 数组加法，也不难
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if(a.length()<b.length()){
            String c = a;
            a=b;
            b=c;
        }
        String result = "";
        int c = 0;
        int al = a.length(),bl=b.length();
        for(int i=1;i<=a.length();i++){
            if(i>b.length()){
                c = a.charAt(al-i)+c-48;
            }else{
                c = a.charAt(al-i)+b.charAt(bl-i)+c-96;
            }
            if(c==0){
                result = "0"+result;
                c=0;
            }else if(c==1){
                result = "1"+result;
                c=0;
            }else if(c==2){
                result = "0"+result;
                c=1;
            }else{
                result = "1"+result;
                c=1;
            }
        }
        if(c==1){
            result = "1"+result;
        }
        return result;
    }
}
