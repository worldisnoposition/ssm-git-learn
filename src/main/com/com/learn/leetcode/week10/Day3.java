package com.learn.leetcode.week10;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Day3 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode now = head;
        int length = 1;
        while (now.next != null) {
            length++;
            now = now.next;
        }
        int a = k % length;
        int b = 0;
        for (int i = 0; i < length - 1 - a; i++) {
            head = head.next;
        }
        now.next = first;
        first = head.next;
        head.next = null;
        return first;
    }
}
