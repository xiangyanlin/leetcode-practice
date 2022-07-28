package org.example.xyl.swordfingeroffer.dp;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2022/7/20
 */
public class DpHardSolution {

    //----------------剑指offer46 丑数---------------

    public int nthUglyNumber1(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }

    //---------------剑指offer60 n个骰子的点数----------------


    /**
     * 1 <= n <= 11
     * 暴力法：遍历所有的点数组合
     * 遍历n个筛子  每个筛子的点数
     */
    double[] pointCont;
    int n;

    public double[] dicesProbability(int n) {

        if (n == 1) {
            double v = 1.0 / 6;
            return new double[]{v, v, v, v, v, v};
        }

        //总点数
        int length = 5 * n + 1;

        //点数从 n 到 6n
        double[] probability = new double[length];

        pointCont = new double[length];
        this.n = n;

        double maxCount = Math.pow(6, n);

        //可变参数 n 点数x 点数数量p   求概率
        process(0, 1, 1);
        process(0, 1, 2);
        process(0, 1, 3);
        process(0, 1, 4);
        process(0, 1, 5);
        process(0, 1, 6);
        for (int i = 0; i < length; i++) {
            probability[i] = pointCont[i]  / maxCount;
        }
        return probability;
    }


    /**
     * 暴力递归
     *
     * @param prePoint 已经决策的点数
     * @param point    当前筛子的点数
     * @param index    决策点
     */
    public void process(int prePoint, int index, int point) {
        int cur = prePoint + point;
        if (index == n) {
            pointCont[cur - n] += 1;
            return;
        }
        //当前总点数为prePoint + point

        process(cur, index + 1, 1);
        process(cur, index + 1, 2);
        process(cur, index + 1, 3);
        process(cur, index + 1, 4);
        process(cur, index + 1, 5);
        process(cur, index + 1, 6);
        //  0 1 6  6 2 6  12 3 6 18 4 6

    }


    /**
     * 动态规划
     */
    public double[] dicesProbability1(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];

            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    //j + k相同的会做概率累加
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }


    public static void main(String[] args) {
        DpHardSolution solution = new DpHardSolution();
        System.out.println(Arrays.toString(solution.dicesProbability(3)));
        System.out.println(Arrays.toString(solution.dicesProbability1(3)));
//        System.out.println(solution.dicesProbability1(2).length);
        //046296296296296294
//        System.out.println(0.004629629629629629 * 9);
    }
}
