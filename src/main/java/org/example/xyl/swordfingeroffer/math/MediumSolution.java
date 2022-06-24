package org.example.xyl.swordfingeroffer.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xiangyanlin
 * @date 2022/6/24
 */
public class MediumSolution {

    //------------------剑指 Offer 14- I. 剪绳子--------------

    public int cuttingRope(int n) {
        return 0;
    }



    //----------------------剑指 Offer 57 - II. 和为s的连续正数序列-----------

    public int[][] findContinuousSequence(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        // (target - 1) / 2 等效于 target / 2 下取整
        int sum = 0, limit = (target - 1) / 2;
        for (int i = 1; i <= limit; ++i) {
            for (int j = i;; ++j) {

                sum += j;
                if (sum > target) {
                    sum = 0;
                    break;
                } else if (sum == target) {
                    int[] res = new int[j - i + 1];
                    for (int k = i; k <= j; ++k) {
                        res[k - i] = k;
                    }
                    vec.add(res);
                    sum = 0;
                    break;
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);

    }

    /**
     * 双指针
     */
    public int[][] findContinuousSequence1(int target) {
        List<int[]> vec = new ArrayList<>();
        for (int l = 1, r = 2; l < r;) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    //---------------------------剑指 Offer 62. 圆圈中最后剩下的数字----------------

    public int lastRemaining(int n, int m) {
        return f(n, m);
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        //对于剩下的 n - 1 个元素，最终会留下第几个元素
        int x = f(n - 1, m);
        return (m + x) % n;
    }


    public int lastRemaining1(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }


    public static void main(String[] args) {
        MediumSolution solution = new MediumSolution();
        System.out.println(solution.lastRemaining(2, 9));
    }

}
