package com.learn.leetcode;

class LeetCode81 {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (target == nums[middle]) {
                return true;
            } else if (nums[right] > nums[middle]) {
                if (target > nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else if (nums[middle] > nums[right]) {
                if (target >= nums[left] && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode81 l = new LeetCode81();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        System.out.println(l.deleteDuplicates(head).val);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newhead = new ListNode(-1);
        ListNode tmp = newhead;

        while (head != null && head.next != null) {
            if (head.val == head.next.val) { //遇到相同的元素，整段删除
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                head = head.next;
            } else {
                tmp.next = head;
                tmp = tmp.next;
                head = head.next;

            }
        }
        tmp.next = head;
        return newhead.next;
    }
}