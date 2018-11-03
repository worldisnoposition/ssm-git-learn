package com.leetcode.week8;

import java.util.*;

public class Day6 {

    /**
     * 跳跃游戏最优答案，反向操作，牛逼的思想
     *
     * @param nums
     * @return
     */
    public boolean canJump1(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if ((i + nums[i]) > lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    /**
     * 跳跃游戏，一个中级题比较快速的做出来了
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > target) {
                break;
            }
            int a = nums[i] + i;
            if (target <= a) {
                target = a;
                if (target >= nums.length) {
                    break;
                }
            }
        }
        return target >= nums.length - 1;
    }
}

class Solution1 {
    /**
     * 合并数字区间我的算法
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size()==0){
            return new ArrayList();
        }
        Comparator<Interval> cpb = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start<o2.start)
                    return -1;
                else if(o1.start>o2.start)
                    return 1;
                else
                    return 0;
            }
        };
        LinkedList<Interval> res = new LinkedList<>();
        Collections.sort(intervals, cpb);
        res.add(intervals.get(0));
        for (int i = 0; i < intervals.size(); i++) {
            Interval now = intervals.get(i);
            Interval last = res.getLast();
            if (last.end < now.start) {
                res.add(now);
            } else if (last.end < now.end) {
                last.end = now.end;
            }
        }
        return res;
    }

    /**
     * 最优答案，我认为别人的算法，快在数组排序上，
     * 加上后面遍历时很巧妙的借用end大于start的特点，减少判断次数
     * 但实话实说，我没仔细看。。。
     * @param intervals
     * @return
     */
    public List<Interval> merge1(List<Interval> intervals) {
        if(intervals==null || intervals.size()==0)
            return new ArrayList<Interval>();
        int start[]=new int[intervals.size()];
        int end[]=new int[intervals.size()];
        for(int i=0;i<intervals.size();i++)
        {
            start[i]=intervals.get(i).start;
            end[i]=intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        List<Interval> res=new ArrayList<>();
        int i=0;
        while(i<intervals.size())
        {
            int st=start[i];
            while(i<intervals.size()-1 && start[i+1]<=end[i])
                i++;
            int e=end[i];
            res.add(new Interval(st,e));
            i++;
        }
        return res;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
