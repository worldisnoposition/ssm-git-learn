package com.leetcode.week5;

public class Day1 {
    /**
     * 数独
     * 一道中级题，其实思想很快就想好了，但是编码时总是又疏忽，运行异常，
     * 新版的leetCode编辑器真难用，影响我开发效率
     * 看了别人的代码我觉得我写的真的很简洁，循环次数也少
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] cache = new int[27][9];
        int temp = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                temp = board[i][j]-49;
                if(cache[i+9][temp]==0
                        &&cache[j+18][temp]==0
                        &&cache[i/3+j/3*3][temp]==0){
                    cache[i+9][temp]=1;
                    cache[j+18][temp]=1;
                    cache[i/3+j/3*3][temp]=1;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
