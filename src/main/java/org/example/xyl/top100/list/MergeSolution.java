package org.example.xyl.top100.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author xiangyanlin
 * @date 2022/9/29
 */
public class MergeSolution {


    /**
     * 合并区间
     * @param intervals 1 <= intervals.length <= 104
     *                   intervals[i].length == 2
     *                   0 <= starti <= endi <= 104
     * @return 合并后的区间
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt((int[] array) -> array[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (merged.size() == 0 ||
                    merged.get(merged.size() - 1)[1] < l) {
                merged.add(new int[]{l, r});
            }
            else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }



    public static void main(String[] args) {
        MergeSolution solution = new MergeSolution();
        int[][] intervals = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 7},
        };
        int[][] arrays = solution.merge(intervals);
        for (int[] array : arrays) {
            System.out.println(Arrays.toString(array));
        }
    }

}
