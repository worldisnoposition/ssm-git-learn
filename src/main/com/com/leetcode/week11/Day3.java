package com.leetcode.week11;

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
}
