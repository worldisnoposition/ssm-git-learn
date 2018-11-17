package com.leetcode.week10;

public class Day6 {
    /**
     * 抄来的答案，但是答案是错的，我修补了一下，好了
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
}
