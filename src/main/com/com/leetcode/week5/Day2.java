package com.leetcode.week5;

public class Day2 {
    /**
     * 数独，用到了深度搜索，和剪枝，但我没用剪枝，导致效率很低，有空还要看看剪枝怎么搞的
     * @param board
     */
    public void solveSudoku(char[][] board) {
        solveSudokuDFS(board,0,0);
    }
    public boolean solveSudokuDFS(char[][] board,int i,int j){
        if(i==9){
            return true;
        }
        if(j>=9){
            return solveSudokuDFS(board,i+1,0);
        }
        if(board[i][j]=='.'){
            for(char k='1';k<='9';k++){
                board[i][j]=k;
                if(isValidSudoku(board)){
                    if(solveSudokuDFS(board,i,j+1)){
                        return true;
                    }
                }
                board[i][j]='.';
            }
        }else{
            return solveSudokuDFS(board,i,j+1);
        }
        return false;
    }

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
