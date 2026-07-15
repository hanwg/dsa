package top.hanwg.dsa.arrays;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// #56 medium
// Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        List<int[]> output = new ArrayList<>();
        output.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] lastInterval = output.getLast();
            if (isOverlapping(lastInterval, intervals[i])) {
                lastInterval[1] = Math.max(lastInterval[1], intervals[i][1]);
            } else {
                output.add(intervals[i]);
            }
        }

        return output.toArray(new int[output.size()][]);
    }

    private boolean isOverlapping(int[] interval1, int[] interval2) {
        return interval2[0] >= interval1[0] && interval2[0] <= interval1[1];
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        Assertions.assertArrayEquals(new int[][]{{1, 5}, {6, 7}}, solution.merge(new int[][]{{1, 3}, {1, 5}, {6, 7}}));

        Assertions.assertArrayEquals(new int[][]{{1, 3}}, solution.merge(new int[][]{{1, 2}, {2, 3}}));
    }
}
