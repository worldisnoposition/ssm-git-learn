package com.leetcode.week7;

public class Day3 {
    /**
     * 我的思路简单暴力但不最优
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int a = matrix.length;
        int[][] result = new int[a][a];
        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                result[j][a-1-i] = matrix[i][j];
            }
        }
        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                matrix[i][j] = result[i][j];
            }
        }
    }

    /**
     * 别人的答案 首先以从对角线为轴翻转，然后再以x轴中线上下翻转即可得到结果
     * @param matrix
     */
    public void rotateRENJIA(int[][] matrix) {
        int n = matrix.length;
        //step 1: transpose
        for (int i = 0; i < n; i++){
            for (int j = 0; j < i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        //step 2: flip horizontally
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n/2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }
}
