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

    /**
     * 这里秩序判断一个格子是否符合，不需要判断整个数独81个格子，怪不得我这么慢
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

/**
 * 判断是否满足数独的isValid只考虑这个格子，而不是整个81个格子，果然快了很多
 */
class Solution {
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
                if(isValid(board,i,j)){
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

    private boolean isValid(char[][] board,int row,int col){
        for(int i = 0;i<9;i++){
            if(i!=col && board[row][i] == board[row][col])
                return false;
        }
        for(int i = 0;i<9;i++){
            if(i!=row && board[i][col] == board[row][col])
                return false;
        }
        int beginRow = 3*(row/3);
        int beginCol = 3*(col/3);
        for(int i = beginRow;i<beginRow+3;i++){
            for(int j = beginCol;j<beginCol+3;j++){
                if(i!=row && j!=col && board[i][j] == board[row][col])
                    return false;
            }
        }
        return true;
    }
}
