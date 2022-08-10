package org.example.xyl.swordfingeroffer;

/**
 * 异或操作
 * @author xiangyanlin
 * @date 2022/2/8
 */
public class XORTest {

    /**
     * 找出两位奇数
     */
    public void two(int[] nums) {
        int eor = 0;
        for (int num:nums){
            eor ^= num;
        }
        //得到 a^b

        //取最右边的1
        int rightOne = eor & (~eor + 1);
        int onlyOne = 0;
        for (int num : nums) {
            if((num & rightOne) == 0) {
                onlyOne ^= num;
            }
        }
        int a=onlyOne;
        int b=eor^a;
        System.out.println(a + "," + b);
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 2, 3, 5, 5};
        XORTest XORTest = new XORTest();
        XORTest.two(nums);
    }
}
