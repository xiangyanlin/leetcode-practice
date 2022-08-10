package org.example.xyl.swordfingeroffer.search;

/**
 * 剑指 Offer 64. 求1+2+…+n
 * @author xiangyanlin
 * @date 2022/6/16
 */
public class SumSolution {

    /**
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     */
    public int sumNums(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        return sum(n);
    }

    public int sum(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        int prevSum = sum(n - 1);
        return prevSum + n;
    }
}
