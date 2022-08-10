package org.example.xyl.swordfingeroffer.bit;

/**
 * @author xiangyanlin
 * @date 2022/6/23
 */
public class CountSolution {


    //----------------------剑指 Offer 15. 二进制中1的个数-----------------------

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if( n % 2 == 1) {
                count ++;
            }
            n = n >>> 1;
        }
        return count;
    }
    //00000000000000000000000000001011
    public int hammingWeight1(Long n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }


    public int hammingWeight2(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    public int hammingWeight3(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }


    //---------------------剑指 Offer 65. 不用加减乘除做加法-----------------------

    /**
     * 无进位和 与 异或运算 规律相同
     * 进位 和 与运算 规律相同（并需左移一位)
     */
    public int add(int a, int b) {
        // 当进位为 0 时跳出
        while(b != 0) {
            // c = 进位
            int c = (a & b) << 1;
            // a = 非进位和
            a ^= b;
            // b = 进位
            b = c;
        }
        return a;

    }

    public int add1(int a, int b) {
        if (b == 0) {
            return a;
        }

        // 转换成非进位和 + 进位
        return add1(a ^ b, (a & b) << 1);
    }

    public static void main(String[] args) {
        CountSolution solution = new CountSolution();
        long t1 = System.currentTimeMillis();
        System.out.println(solution.hammingWeight(11));
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1));
//
//        System.out.println(solution.hammingWeight1(4294967293L));
//        long t3 = System.currentTimeMillis();
//        System.out.println((t3 - t2));
    }

}
