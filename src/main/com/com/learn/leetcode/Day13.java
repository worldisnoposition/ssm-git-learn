package com.learn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {
    /**
     * 去最接近某数的4数和，我的答案太烂了，人家标准答案的很好，要学习思想，n个数的和等于目标数的这类题有空好好看看
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();
        for(int i=0;i<nums.length-3;i++){
            for(int j=i+1;j<nums.length-2;j++){
                for(int k=j+1;k<nums.length-1;k++){
                    int temp = (nums[i]+nums[j]+nums[k]);
                    for(int h=nums.length-1;h>k;h--){
                        // System.out.println((nums[i]+"-"+nums[j]+"-"+nums[k]+"-"+nums[h]));
                        if((temp+nums[h])==target){
                            List<Integer> add = new ArrayList();
                            add.add(nums[i]);
                            add.add(nums[j]);
                            add.add(nums[k]);
                            add.add(nums[h]);
                            if(!result.contains(add)){
                                result.add(add);
                            }
                        }else if((temp+nums[h])>target){
                            continue;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 单项链表移除倒数第n个
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null){
            return null;
        }
        ListNode temp = head;
        int length = 1;
        while(temp.next!=null){
            temp=temp.next;
            length++;
        }
        length=length-n;
        temp = head;
        if(length==0){
            return head.next;
        }
        while(length>1){
            temp = temp.next;
            length--;
        }
        System.out.println(temp.val);
        if(temp.next!=null){
            temp.next=temp.next.next;
        }
        return head;
    }

}
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
