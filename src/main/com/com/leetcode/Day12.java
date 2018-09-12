package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Day12 {
    /**
     * 这道中级题还比较简单很快做出来了
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if("".equals(digits)){
            return new ArrayList();
        }
        String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result = new ArrayList();

        for(int i=0;i<digits.length();i++){
            String temp = map[digits.charAt(i)-48];
            if(result.size()==0){
                for(int j=0;j<temp.length();j++){
                    result.add(temp.charAt(j)+"");
                }
            }else{
                List<String> tempList = new ArrayList();
                for(int k=0;k<result.size();k++){
                    for(int j=0;j<temp.length();j++){
                        tempList.add(result.get(k)+temp.charAt(j));
                    }
                }
                result = tempList;
            }
        }
        return result;
    }
}
