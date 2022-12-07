package org.example.xyl.top100.dp;

/**
 * @author xiangyanlin
 * @date 2022/12/7
 */
public class ClimbSolution {

    /**
     * 爬楼梯
     *
     * @param n 1 <= n <= 45
     * @return 方式数量
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        return process(0, n);
    }

    public int process(int index, int n) {
        if (index == n) {
            return 1;
        }
        if (index < n - 1) {
            return process(index + 1, n) + process(index + 2, n);
        }
        return process(index + 1, n);
    }

    /**
     * 动态规划
     *
     * @param n 1 <= n <= 45
     * @return 方式数量
     */
    public int climbStairsDp(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i > 1) {
                dp[i] = dp[i - 1] + dp[i - 2];
                continue;
            }
            dp[i] = dp[i - 1];
        }
        return dp[n ];
    }

    public static void main(String[] args) {
        ClimbSolution solution = new ClimbSolution();
        System.out.println(solution.climbStairs(4));
        System.out.println(solution.climbStairsDp(4));
    }
}
