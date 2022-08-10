package org.example.xyl.swordfingeroffer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 动态规划
 *
 * @author xiangyanlin
 * @date 2022/4/4
 */
public class DynamicProgramSolution {


    /**
     * 斐波那契（Fibonacci）数列
     * 时间复杂度O(n)
     */
    int MOD = 1000000007;

    public int fib(int n) {

        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int fib = 1, fib1 = 0, fib2 = 0;
        for (int i = 2; i <= n; i++) {
            fib2 = fib1;
            fib1 = fib;
            fib = (fib1 + fib2) % MOD;
        }
        return fib;
    }

    /**
     * 矩阵快速幂
     * {                           {
     * {1, 1},    *         {1},
     * {1, 0}               {0}
     * } 的n次方;                                  }
     */
    public int matrixFib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];

    }

    /**
     * 矩阵快速幂
     * {                           {                     {                           {
     * {1, 1},    *         {1},                          {1, 1},    *         {1, 0},
     * {1, 0}               {0}           =                {1, 0}              {0, 1}
     * } 的n次方;                                  }                        }的n次方 ;            }                       }
     */
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        // ret * a的 n次方
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (int)
                        (((long) a[i][0] * b[0][j] +
                                (long) a[i][1] * b[1][j])
                                % MOD);
            }
        }
        return c;
    }

    /**
     * 青蛙跳台阶
     * 此类求 多少种可能性 的题目一般都有 递推性质 ，即 f(n)f(n) 和 f(n-1)f(n−1)…f(1)f(1) 之间是有联系的。
     * f(n)=f(n−1)+f(n−2)
     * fib1 = f(n-1) fib2 = f(n-1) fib = f(n)
     * 解法一：动态规划
     */
    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        int fib1 = 1, fib2 = 1, fib = 1;
        for (int i = 2; i <= n; i++) {
            fib2 = fib1;
            fib1 = fib;
            fib = (fib1 + fib2) % MOD;
        }
        return fib;
    }

    /**
     * 解法二 矩阵快速幂
     */
    public int matrixNumWays(int n) {
        if (n <= 1) {
            return 1;
        }
        int[][] q = {{1, 1}, {1, 0}};
        //q的n次方 * {{1,1} }  /q的n次方 * {{1,0}, {1, 1}}
        int[][] ret = {{1, 0}, {1, 1}};
        while (n > 0) {
            //n的最后一位是1 n为奇数   执行ret * q的n次方
            //若n=9（ ret * q ） n=9
            // * q的二次方* q的四次方 n = 1而此时 n的值为奇数
            if ((n & 1) == 1) {
                ret = multiply(ret, q);
            }
            //右移动减小
            n >>= 1;
            q = multiply(q, q);
        }
        return ret[0][0];
    }


    /**
     * 股票最大动力
     */
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 1) {
            return 0;
        }
        //暴力解法
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int monkey = prices[i] - prices[j];
                if (monkey > max) {
                    max = monkey;
                }
            }

        }
        return max;
    }

    public int maxProfit1(int[] prices) {
        if (null == prices || prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];

            } else if ((prices[i] - min) > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    /**
     * 子数组最大和
     * 动态规划解法 O(n)
     */
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum < num) {
                sum = num;
            }
            if (max < sum) {
                max = sum;
            }
        }
        return max;

    }

    public int maxSubArray1(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        //中点
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }


    /**
     * 遍历每个点的最大值
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                if(i == 0) {
                    grid[i][j] += grid[i][j - 1] ;
                } else if(j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[m - 1][n - 1];


    }


    /**
     * 把数字翻译成字符串
     */
    public int translateNum(int num) {

        return f(num);
    }
    public int f(int num){
        if(num < 10) {
            return 1;
        }
        if (num % 100 < 26 && num % 100 > 9) {
            return f(num / 10) + f(num / 100);
        } else {
            return f(num / 10);
        }
    }

    /**
     * 最长不重复子串
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        DynamicProgramSolution d = new DynamicProgramSolution();
        System.out.println(d.lengthOfLongestSubstring("peekew"));
        System.out.println(d.fib(5));
    }
}
