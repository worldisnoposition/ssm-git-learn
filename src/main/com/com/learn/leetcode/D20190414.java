package com.learn.leetcode;

public class D20190414 {
    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        h.next = new ListNode(4);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(2);
        h.next.next.next.next = new ListNode(5);
        h.next.next.next.next.next = new ListNode(2);
        ListNode a = partition(h,3);
    }
    public static ListNode partition(ListNode head, int x) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode insertNode = null;
        ListNode curPre = preHead;
        ListNode cur = head;
        while(cur != null){
           if(cur.val>=x&&insertNode == null){
               insertNode=curPre;
           }
           if(cur.val<x&&insertNode!=null){
               curPre.next = curPre.next.next;
               cur.next=insertNode.next;
               insertNode.next=cur;
               cur=curPre.next;
               continue;
           }
           curPre = curPre.next;
           cur = cur.next;
        }
        return preHead.next;
    }
}
