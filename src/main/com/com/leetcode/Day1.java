package com.leetcode;

public class Day1 {
    /**
     * public int[] twoSum(int[] nums, int target) {
     * for (int i = 0; i < nums.length; i++) {
     * for (int j = i + 1; j < nums.length; j++) {
     * if (nums[j] == target - nums[i]) {
     * return new int[] { i, j };
     * }
     * }
     * }
     * throw new IllegalArgumentException("No two sum solution");
     * }
     * 取得数组里两个数的和等于目标数字的
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        out:
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = target - nums[i];
            in:
            for (int j = i + 1; j < nums.length; j++) {
                if (temp == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
     * ListNode result = new ListNode(-1);
     * ListNode head = result;
     * int carry = 0;
     * int sum = 0;
     * while(l1!=null && l2!=null)
     * {
     * sum = l1.val + l2.val + carry;
     * <p>
     * carry= sum/10;
     * sum = sum %10;
     * <p>
     * result.next = new ListNode(sum);;
     * result = result.next;
     * <p>
     * l1= l1.next;
     * l2 = l2.next;
     * }
     * <p>
     * while(l1!=null)
     * {
     * sum = l1.val + carry;
     * carry= sum/10;
     * sum = sum %10;
     * <p>
     * result.next = new ListNode(sum);
     * result = result.next;
     * l1 = l1.next;
     * }
     * <p>
     * while(l2!=null)
     * {
     * sum = l2.val + carry;
     * carry= sum/10;
     * sum = sum %10;
     * <p>
     * result.next = new ListNode(sum);
     * result = result.next;
     * l2 = l2.next;
     * }
     * <p>
     * if(carry!= 0)
     * {
     * result.next = new ListNode(carry);
     * }
     * return head.next;
     * <p>
     * }
     *两个list求和（每个node相当与一位数字），输出倒序的list
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = null, now = null;
        int c = 0, b = 0, a = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                a = l1.val;
                l1 = l1.next;
            } else {
                a = 0;
            }
            if (l2 != null) {
                b = l2.val;
                l2 = l2.next;
            } else {
                b = 0;
            }
            c += a + b;
            if (first != null) {
                now.next = new ListNode(c % 10);
                now = now.next;
            } else {
                first = new ListNode(c % 10);
                now = first;
            }
            c = c / 10;
        }
        if (c != 0) {
            now.next = new ListNode(c);
        }
        return first;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
