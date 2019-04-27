package com.learn.leetcode.y20190121;

import java.util.LinkedList;
import java.util.List;

public class D20190421 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public List<Integer> inorderTraversal(D20190416.TreeNode root) {
            List<Integer> result = new LinkedList();
            putToList(root, result);
            return result;
        }

        private void putToList(D20190416.TreeNode root, List<Integer> result){
            if(root != null){
                putToList(root.left, result);
                result.add(root.val);
                putToList(root.right, result);
            }
        }
    }
}
