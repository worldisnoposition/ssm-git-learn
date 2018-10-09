package com.leetcode.week6;

import java.util.ArrayList;
import java.util.List;

public class Day2 {
    /**
     * 我的办法能实现但不优，实际上把list作为参数更好，我这样用list做返回值效率很低，new了很多次对象
    * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if (nums == null || nums.length == 0) {
            return new ArrayList();
        } else if (nums.length == 1) {
            List<Integer> temp = new ArrayList();
            temp.add(nums[0]);
            result.add(temp);
        } else {
            for (int i = 0; i < nums.length; i++) {
                int[] next = remove(nums, i);
                List<List<Integer>> nextList = permute(next);
                for (int j = 0; j < nextList.size(); j++) {
                    List<Integer> temp = new ArrayList();
                    temp.add(nums[i]);
                    temp.addAll(nextList.get(j));
                    result.add(temp);
                }
            }
        }
        return result;
    }

    public int[] remove(int[] nums, int toremove) {
        if (nums != null && nums.length != 0) {
            int[] result = new int[nums.length - 1];
            for (int i = 0, j = 0; i < nums.length; i++) {
                if (i == toremove) {
                    continue;
                }
                result[j++] = nums[i];
            }
            return result;
        }
        return null;
    }


}
