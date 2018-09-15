package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Day14 {
    /**
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character,Character> temp = new HashMap();
        temp.put('(',')');
        temp.put('{','}');
        temp.put('[',']');
        LinkedList<Character> a = new LinkedList();
        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(a.size()>0&&c.equals(temp.get(a.getLast()))){
                a.pollLast();
            }else{
                a.add(c);
            }
        }
        return a.size()==0;
    }

    /**
     * 虽然是个简单题，但是更好的答案用到了递归的方法省掉了我所用的变量，又省内存，又快
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 我的垃圾算法
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsWODE(ListNode l1, ListNode l2) {
        if(l1==null)return l2;
        if(l2==null)return l1;
        ListNode result = null;
        ListNode first = null;
        while(true){
            if(l1==null){
                result.next = l2;
                return first;
            }
            if(l2==null){
                result.next = l1;
                return first;
            }
            if(l1.val>l2.val){
                if(result==null){
                    result = l2;
                    first=result;
                }else{
                    result.next = l2;
                    result = result.next;
                }
                l2=l2.next;
            }else{
                if(result==null){
                    result = l1;
                    first=result;
                }else{
                    result.next = l1;
                    result = result.next;
                }
                l1=l1.next;
            }
        }
    }
}
