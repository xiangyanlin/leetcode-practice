package org.example.xyl.top100.dp;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2023/1/6
 */
public class CoinSolution {


    /**
     * 零钱兑换
     * 暴力递归
     * @param coins  1 <= coins.length <= 12
     *               1 <= coins[i] <= 231 - 1
     * @param amount 0 <= amount <= 104
     * @return 最少组合数
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return 0;
        }
        int res = process(coins, 0, amount, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 递归方法
     * @param coins 硬币集合
     * @param index 选择第几种硬币
     * @param rest 剩余金额
     * @return
     */
    public static int process(int[] coins, int index, int rest, int useNumber) {
        // 无面值的时候
        if (index == coins.length) {
            return rest == 0 ? useNumber : Integer.MAX_VALUE;
        }
        // 有面值的时候
        int minNumber = Integer.MAX_VALUE;
        // coins[index] 当钱面值
        for (int i = 0; i * coins[index] <= rest; i++) {
            int cur = process(coins, index + 1, rest - i * coins[index], useNumber + i);
            minNumber = Math.min(minNumber, cur);
        }
        return minNumber;
    }


    /**
     * 零钱兑换
     * 动态规划
     * @param coins  1 <= coins.length <= 12
     *               1 <= coins[i] <= 231 - 1
     * @param amount 0 <= amount <= 104
     * @return 最少组合数
     */
    public int coinChangeDp(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if (coin <= i) {
                    dp[i] = Math.min(
                            dp[i], dp[i - coin] + 1
                    );
                }

            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        CoinSolution solution = new CoinSolution();
        int[] coins = {1 ,2 ,5};
        System.out.println(solution.coinChangeDp(coins, 11));
        int[] coins1 = {2};
        System.out.println(solution.coinChangeDp(coins1, 3));
    }
}
