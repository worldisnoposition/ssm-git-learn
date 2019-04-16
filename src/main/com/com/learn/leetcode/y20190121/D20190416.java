package com.learn.leetcode.y20190121;

import java.util.LinkedList;

public class D20190416 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p != null && q != null) {
                if (p.val == q.val) {
                    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
                }
            }
            return false;
        }
    }

    public boolean isValidBST(TreeNode root) {
        // if(root!=null){
        //     if(root.left!=null&&root.val<=root.left.val){
        //         return false;
        //     }
        //     if(root.right!=null&&root.val>=root.right.val){
        //         return false;
        //     }
        //     return isValidBST(root.left)&&isValidBST(root.right);
        // }
        // return true;
        LinkedList<Integer> l = new LinkedList();
        putInList(root,l);
        Integer val = null;
        for(Integer v:l){
            if(val==null){
                val = v;
                continue;
            }
            if(v<=val){
                return false;
            }else{
                val=v;
            }
        }
        return true;
    }
    public void putInList(TreeNode root, LinkedList l){
        if(root!=null){
            putInList(root.left,l);
            l.add(root.val);
            putInList(root.right,l);
        }
    }
}
