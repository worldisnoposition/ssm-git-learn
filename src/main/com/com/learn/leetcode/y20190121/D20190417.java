package com.learn.leetcode.y20190121;

public class D20190417 {
    public static void main(String[] args) {
        D20190417 d = new D20190417();
        char[][] matrix = {
                {'1','0','1','1','0','1'},
                {'1','1','1','1','1','1'},
                {'0','1','1','0','1','1'},
                {'1','1','1','0','1','0'},
                {'0','1','1','1','1','1'},
                {'1','1','0','1','1','1'}};
        System.out.println(d.maximalRectangle(matrix));
    }
    public int maximalRectangle(char[][] matrix) {
        int height = matrix.length-1;
        if(height < 0){
            return 0;
        }
        int weight = matrix[0].length-1;
        int[][] temp = new int[height+1][weight+1];
        for(int k=weight;k>=0;k--){
            int val=0;
            for(int j=height;j>=0;j--){
                if(matrix[j][k]=='1'){
                    ++val;
                }else{
                    val=0;
                }
                temp[j][k]=val;
            }
        }
        int result = 0,t=0;
        for(int i=0;i<=height;i++){
            t=max(temp[i]);
            System.out.println(t);
            result = result>t?result:t;
        }
        return result;
    }
    public int max(int[] row){
        int[] result=new int[row.length];
        int max=0;
        for(int i=0;i<row.length;i++){
            int k=i-1;
            while(k>=0&&row[k]>=row[i]){
                k--;
            }
            int j=i+1;
            while(j<row.length&&row[j]>=row[i]){
                j++;
            }
            result[i]=(j-k-1)*row[i];
            if(result[i]>max){
                max=result[i];
            }
        }
        return max;
    }

}
