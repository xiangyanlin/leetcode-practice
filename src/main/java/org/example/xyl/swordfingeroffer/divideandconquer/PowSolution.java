package org.example.xyl.swordfingeroffer.divideandconquer;

/**
 * 剑指 Offer 16. 数值的整数次方
 *
 * @author xiangyanlin
 * @date 2022/6/22
 */
public class PowSolution {

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? dfs(x, N) : 1.0 / dfs(x, -N);
    }


    public double dfs(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        return dfs(x, N / 2) * dfs(x, N - (N / 2));
    }

    public double myPow1(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }


    public static void main(String[] args) {
        PowSolution solution = new PowSolution();

        long t1 = System.currentTimeMillis();
        double v = solution.myPow(2, -2);
        long t2 = System.currentTimeMillis();
        System.out.println(v + ":" +( t2 - t1));

        double v1 = solution.myPow(0.00001, 2147483647);
        long t3 = System.currentTimeMillis();
        System.out.println(v1 + ":" +( t3 - t2));

        double v2 = solution.myPow1(2, -2);
        long t4 = System.currentTimeMillis();
        System.out.println(v2 + ":" +( t4 - t3));


        double v3 = solution.myPow1(0.00001, 2147483647);
        long t5 = System.currentTimeMillis();
        System.out.println(v3 + ":" +( t5 - t4));

        System.out.println();
    }


    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul2(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }


}
