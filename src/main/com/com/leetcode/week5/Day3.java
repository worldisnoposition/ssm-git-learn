package com.leetcode.week5;

import java.util.*;

public class Day3 {
    /**
     * 一道中级题，我感觉这道题，还是能找到一些做题技巧的了
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates.length==0){
            return new ArrayList();
        }
        Arrays.sort(candidates);
        return combinationSum1(candidates,0,target);
    }
    public List<List<Integer>> combinationSum1(int[] candidates, int start, int target){
        List<List<Integer>> result = new ArrayList();
        for(int i=start;i<candidates.length;i++){
            if(candidates[i]<target){
                List<List<Integer>> temp = combinationSum1(candidates,i,target-candidates[i]);
                for(int j=0;j<temp.size();j++){
                    List<Integer> add = new ArrayList();
                    add.add(candidates[i]);
                    add.addAll(temp.get(j));
                    result.add(add);
                }
            }else if(candidates[i]==target){
                List<Integer> add = new ArrayList();
                add.add(candidates[i]);
                result.add(add);
            }else{
                break;
            }
        }
        return result;
    }

    /**
     * 88 777
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length==0){
            return new ArrayList();
        }
        Arrays.sort(candidates);
        return combinationSum21(candidates,0,target);
    }
    private Map<String,Object> cache = new HashMap();
    public List<List<Integer>> combinationSum21(int[] candidates, int start, int target){
        List<List<Integer>> result = new ArrayList();
        for(int i=start;i<candidates.length;i++){
            //注意这个条件i>start是这个题的精髓，不重复，而且要从i>start开始
            if(i>start&&candidates[i]==candidates[i-1]){
                continue;
            }
            if(candidates[i]<target){
                List<List<Integer>> temp = combinationSum21(candidates,i+1,target-candidates[i]);
                for(int j=0;j<temp.size();j++){
                    List<Integer> add = new ArrayList();
                    add.add(candidates[i]);
                    add.addAll(temp.get(j));
                    result.add(add);
                }
            }else if(candidates[i]==target){
                List<Integer> add = new ArrayList();
                add.add(candidates[i]);
                result.add(add);
            }else{
                continue;
            }
        }
        return result;
    }
}
