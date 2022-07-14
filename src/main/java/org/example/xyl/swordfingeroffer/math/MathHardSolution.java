package org.example.xyl.swordfingeroffer.math;

/**
 * @author xiangyanlin
 * @date 2022/7/14
 */
public class MathHardSolution {

    //------------------- 剑指offer 14-2 剪绳子-----------

    /**
     * 相对于14-2  多了 大数求余问题
     * 2 <= n <= 1000
     */
    int mod = 1000000007;
    public int cuttingRope(int n) {
        if(n <= 3) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        //快速幂 x ^ a % p = ( x ^ 2 % p) ^ (a/2) % p
        for(int a = n / 3 - 1; a > 0; a /= 2) {
            //奇数
            if(a % 2 == 1) {
                rem = (rem * x) % p;
            }
            x = (x * x) % p;
        }
        if(b == 0) {
            return (int)(rem * 3 % p);
        }
        if(b == 1) {
            return (int)(rem * 4 % p);
        }
        return (int)(rem * 6 % p);
    }


    public int cuttingRopeNo(int n) {
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

}
