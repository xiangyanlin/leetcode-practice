package org.example.xyl.swordfingeroffer.dp;

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
     */
    public double[] dicesProbability(int n) {
        return null;
    }


    public static void main(String[] args) {
        DpHardSolution solution = new DpHardSolution();
        int i1 = solution.nthUglyNumber1(18);
        System.out.println(i1);
    }
}
