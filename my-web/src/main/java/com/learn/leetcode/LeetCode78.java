package com.learn.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode78 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList();
            List<Integer> list = new ArrayList();
            add(result,0,nums,list);
            return result;
        }
        private void add(List<List<Integer>> result,int index,int[] nums,List<Integer> list){
            result.add(new ArrayList(list));
            for(int i=index;i<nums.length;i++){
                list.add(nums[i]);
                add(result,i+1,nums,list);
                list.remove(list.size()-1);
            }
        }
}
