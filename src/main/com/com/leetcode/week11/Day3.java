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
}
