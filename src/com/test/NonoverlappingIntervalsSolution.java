package com.test;

import java.util.*;
/*
    Input: [[1,2],[2,3],[3,4],[1,3]]
    Output: 1
    Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

    Input: [[1,2],[1,2],[1,2]]
    Output: 2
    Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

    Input: [[1,2],[2,3]]
    Output: 0
 */
public class NonoverlappingIntervalsSolution{
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length<2)return 0;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int end = intervals[0][1];
        int min = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                end = Math.min(end, intervals[i][1]);
                min++;
            } else {
                end = intervals[i][1];
            }
        }
        return min;
    }
}