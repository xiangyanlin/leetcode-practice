package org.example.xyl.swordfingeroffer;

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
        int fib =1, fib1 = 0, fib2 =0;
        for (int i = 2; i <= n; i++) {
            fib2 = fib1;
            fib1 = fib;
            fib = (fib1 + fib2) % MOD;
        }
        return fib;
    }

    /**
     * 矩阵快速幂
     *      {                           {
     *                 {1, 1},    *         {1},
     *                 {1, 0}               {0}
     *         } 的n次方;                                  }
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
     *      {                           {                     {                           {
     *                 {1, 1},    *         {1},                          {1, 1},    *         {1, 0},
     *                 {1, 0}               {0}           =                {1, 0}              {0, 1}
     *         } 的n次方;                                  }                        }的n次方 ;            }                       }
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
        int fib1 = 1, fib2 = 1, fib=1;
        for (int i = 2; i <=n; i++) {
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
        int[][] ret =  {{1,0}, {1, 1}};
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
            for (int j = i-1; j >= 0; j--) {
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

            } else if(( prices[i] - min) > max){
                max = prices[i] - min;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        DynamicProgramSolution d = new DynamicProgramSolution();
        System.out.println(d.fib(5));
    }
}
