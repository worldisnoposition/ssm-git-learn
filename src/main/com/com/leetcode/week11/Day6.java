package com.leetcode.week11;

import java.util.ArrayList;

public class Day6 {
    /**
     * 矩阵置零，这题不难，我的算法如下，性能不佳
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        ArrayList<Integer> row = new ArrayList();
        ArrayList<Integer> col = new ArrayList();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for(Integer r:row){
            for(int i=0;i<matrix[0].length;i++){
                matrix[r][i]=0;
            }
        }
        for(Integer c:col){
            for(int i=0;i<matrix.length;i++){
                matrix[i][c]=0;
            }
        }
    }

    /**
     * 最优选择
     * 1.遍历每个元素
     * 2.遇到0，把第一行对应的列置零
     * 3.遍历完每一行，如果存在0，整行置零
     * 4.全部遍历完后，去看看第一行为0的，对应0的列都置零
     * 5.遍历第一行时，记录一下是否有0，最后处理第一行是否要置零
     *
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        boolean firstRowZero = false;
        for (int i = 0; i < matrix.length; i++) {
            boolean zeroRow = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRow = true;
                    matrix[0][j] = 0;
                }
            }
            if (i == 0) {
                firstRowZero = zeroRow;
            } else if (zeroRow) {
                for (int j = 0; j < matrix[i].length; j++)
                    matrix[i][j] = 0;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < matrix.length; i++)
                    matrix[i][j] = 0;
            }
        }
        if (firstRowZero) {
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        }
    }
}
