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


    //-------------剑指offer43 1到n 1出现的次数------------


    public int countDigitOne(int n) {
        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k）
        // 但为了让代码看起来更加直观，这里保留了 k
        long mulk = 1;
        int ans = 0;
        //循环遍历每个数位上的1
        while (n >= mulk) {
            long nS = n % (mulk * 10);
            ans += (n / (mulk * 10)) * mulk +
                    Math.min(Math.max(nS - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

    //---------------剑指offer44 数字序列的某一位数字 ----------------------------


    /**
     * 0123456789101112131415
     */
    public int findNthDigit(int n) {
        // n =15
        //位数
        int digit = 1;
        //起始数字
        long start = 1;
        //数位 的字符数量
        long count = 9;
        // 1.确定 n 所在 数字 的 位数 ，记为 digit ； 1
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        // n =15 - 9 = 6 num = 12
        // 2.确定 n 所在的 数字 ，记为 num ； (n - 1) / digit = 1
        long num = start + (n - 1) / digit;
        // 3.确定 n 是 num 中的哪一数位，并返回结果。 (n - 1) % digit
        return Long.toString(num).charAt((n - 1) % digit) - '0';

    }




}
