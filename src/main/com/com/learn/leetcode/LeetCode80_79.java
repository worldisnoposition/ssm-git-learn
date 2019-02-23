package com.learn.leetcode;

public class LeetCode80_79 {
    int[] dh = {0, 1, 0, -1};
    int[] dw = {1, 0, -1, 0};
    private boolean[][] isUsed;
    public boolean exist(char[][] board, String word) {
        isUsed = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(isWord(board,i,j,word,0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWord(char[][] board, int i, int j, String word, int now){

        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || isUsed[i][j]){
            return false;
        }
        if(word.charAt(now)==board[i][j]){
            if(word.length() == now+1){
                return true;
            }else{
                isUsed[i][j] = true;
                for(int k=0;k<4;k++){
                    if(isWord(board,i+ dh[k],j+ dw[k],word,now+1)){
                        return true;
                    }
                }
                isUsed[i][j] = false;
            }
        }
        return false;
    }
    public int removeDuplicates(int[] nums) {
        int result = 2;
        if(nums.length<2){
            return nums.length;
        }
        for(int i=2;i<nums.length;i++){
            if(nums[i] != nums[result-2]){
                nums[result++] = nums[i];
            }

        }
        return result;
    }

}
