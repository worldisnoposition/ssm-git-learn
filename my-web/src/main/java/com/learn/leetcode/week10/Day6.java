package com.learn.leetcode.week10;

import java.util.ArrayList;
import java.util.List;

public class Day6 {
    /**
     * 抄来的答案，但是答案是错的，我修补了一下，好了
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if ((intervals == null || intervals.size() == 0) && newInterval == null) {
            return res;
        }

        int i = 0;
        int n = intervals.size();
        while (i < n && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));
        }

        int mergeLeft = newInterval.start;
        int mergeRight = newInterval.end;
        while (i < n && intervals.get(i).start <= newInterval.end) {
            // System.out.println(intervals.get(i).start+"--"+newInterval.start);
            mergeLeft = Math.min(intervals.get(i).start, mergeLeft);
            mergeRight = Math.max(intervals.get(i).end, mergeRight);
            // System.out.println(mergeLeft+"//"+mergeRight);
            i++;
        }
        res.add(new Interval(mergeLeft, mergeRight));

        while (i < n) {
            res.add(intervals.get(i));
            i++;
        }

        return res;
    }

    /**
     * 更好的答案，这个答案，比上一个，好就好在这个if和else if，减少了计算的次数
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null) return res;
        int start = newInterval.start;
        int end = newInterval.end;
        int i = 0;
        for (Interval interval : intervals) {
            //这个答案，比上一个，好就好在这个if和else if，减少了计算的次数
            if (interval.end < start) {
                res.add(interval);
                i++;
            } else if (interval.start > end) {
                res.add(interval);
            } else {
                start = Math.min(start, interval.start);
                end = Math.max(end, interval.end);
            }
        }
        res.add(i, new Interval(start, end));
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
