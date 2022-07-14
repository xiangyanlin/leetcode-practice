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

    /**
     * 2 <= n <= 58
     */
    public int cuttingRope(int n) {
        if(n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3;
        //3的倍数
        if(b == 0) {
            return (int)Math.pow(3, a);
        }
        //余1
        if(b == 1) {
            return (int)Math.pow(3, a - 1) * 4;
        }
        //余2
        return (int)Math.pow(3, a) * 2;
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

    /**
     * 本题是著名的 “约瑟夫环” 问题
     */
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


    /**
     * 本题是著名的 “约瑟夫环” 问题
     * 删除的数字为 (m−1)%n
     * 设 t =  m%n 删除一个后 t,t+1,t+2,...,0,1,...,t−3,t−2   t-1被删掉了
     * f(n)
     * =(f(n−1)+t)%n
     * =(f(n−1)+m%n)%n
     * =(f(n−1)+m)%n
     *
     */
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
