package org.example.xyl.swordfingeroffer.bit;

/**
 * 位运算
 *
 * @author xiangyanlin
 * @date 2022/6/23
 */
public class BitSolution {

    //-------------------剑指 Offer 56 - I. 数组中数字出现的次数---------------------

    public int[] singleNumbers(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        //得到出现1次的两个数 eor = a^b

        //取二进制数eor   取最右边的1  如    1001000100 ==》 100
        int rightOne = eor & (~eor + 1);
        int onlyOne = 0;
        for (int num : nums) {
            //num rightOne 位置 为  0
            if ((num & rightOne) == 0) {
                //将数据区分为该位置为0的
                // 因为（ a的此位置 ^  b的此位置） == 1
                //所以 也就是 a 只有一个该位置为0
                // 所以a b 只有一个在这边
                onlyOne ^= num;
            }
        }
        int a = onlyOne;
        int b = eor ^ a;
        return new int[]{a, b};
    }

    //------------------剑指 Offer 56 - II. 数组中数字出现的次数 II-------------------


    public int singleNumber(int[] nums) {
        int[] counts = new int[32];

        //统计每个二进制位1出现的次数
        for(int num : nums) {
            for(int j = 0; j < 32; j++) {
                // 更新第 j 位
                counts[j] += num & 1;
                // 第 j 位 --> 第 j + 1 位
                num >>>= 1;
            }
        }

        int res = 0, m = 3;
        for(int i = 0; i < 32; i++) {
            res <<= 1;
            // 得到 只出现一次的数字 的第 (31 - i) 位
            int rem = counts[31 - i] % m;

            //用或操作把每一位连接起来
            res |= rem;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(0 ^ 2);
        System.out.println(0 & 2);
        BitSolution solution = new BitSolution();
        int[] nums = {1, 1, 2, 4, 5, 5};
        solution.singleNumbers(nums);
    }
}
