package org.example.xyl.top100.string.array;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2023/2/21
 */
public class ArraySolution {


    /**
     * 计数质数
     * 枚举法
     * @param n 0 <= n <= 5 * 106
     * @return 质数数量
     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计数质数
     * 埃氏筛
     * @param n 0 <= n <= 5 * 106
     * @return 质数数量
     */
    public int countPrimes1(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }


}