package com.learn.leetcode;

public class Day15 {
    /**
     * 假设又lists的size是n,每个list长l，我的时间复杂度就是n*n*l，是n个一起归并，
     * 但是又更好的答案 n*l*lgn,就是两两归并
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode first = null;
        ListNode result = null;
        ListNode miniVal = null;
        int flag = 0;
        while(true){
            boolean stop = true;
            for(int i=0;i<lists.length;i++){
                ListNode l = lists[i];
                if(l!=null&&(miniVal==null||miniVal.val>l.val)){
                    miniVal = l;
                    flag = i;
                    stop = false;
                }
            }
            if(stop){
                break;
            }
            lists[flag]=lists[flag].next;
            if(first == null){
                result = miniVal;
                first = new ListNode(0);
                first.next = result;
            }else{
                result.next = miniVal;
                result = result.next;
            }
            miniVal = null;
        }
        return first==null?null:first.next;
    }

    /**
     * 这题其实就是string.indexOf()函数，而且直接用这个函数居然更快，我也是服了
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(needle.length()==0){
            return 0;
        }
        for(int i=0;i<haystack.length();i++){
            for(int j=0;j<needle.length();j++){
                if(i+j>=haystack.length()){
                    return -1;
                }
                if(haystack.charAt(i+j)!=needle.charAt(j)){
                    break;
                }
                if(j==needle.length()-1){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 这类链表题用递归太好用了，走了一些弯路终于想到了，之前就有一道，我记不清了，基本就是我的实现很麻烦，人家用递归的很快
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode second = head.next;
        head.next = swapPairs(head.next.next);
        second.next=head;
        return second;
    }

    /**
     * 写的不太优雅，但起码一道hard题是对的，基本思路也可以，我觉得算不上hard题，中级题吧
     * 我用的递归，其实完全没必要，完全可以用循环实现，循环比递归少了方法调用以及临界值判断的次数，更快
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k==0){
            return head;
        }
        ListNode[] contain = new ListNode[k];
        int i=0;
        while(true){
            if(i==k){
                break;
            }
            if(head==null){
                return contain[0];
            }
            contain[i]=head;
            i++;
            head=head.next;
        }
        for(int j=i;j>1;j--){
            contain[j-1].next=contain[j-2];
        }
        if(head!=null&&head.next!=null){
            contain[0].next=reverseKGroup(head,k);
        }else{
            contain[0].next = head;
        }
        return contain[i-1];

    }
}
