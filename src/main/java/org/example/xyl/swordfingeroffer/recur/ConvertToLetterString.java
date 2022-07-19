package org.example.xyl.swordfingeroffer.recur;

/**
 * 数字转字符
 * 从左到右模型
 *
 * @author xiangyanlin
 * @date 2022/7/10
 */
public class ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }


    /**
     * 数字转字符
     * i之前的位置，如何转化已经做过决定了, 不用再关心
     * i... 有多少种转化的结果
     * @return 有多少不同的可能性
     */
    public static int process(char[] str, int i) {
        // base case
        if (i == str.length) {
            return 1;
        }
        // i没有到终止位置
        if (str[i] == '0') {
            return 0;
        }
        // str[i]字符不是‘0’
        if (str[i] == '1') {
            // i自己作为单独的部分，后续有多少种方法
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                // (i和i+1)作为单独的部分，后续有多少种方法
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            // i自己作为单独的部分，后续有多少种方法
            int res = process(str, i + 1);
            // (i和i+1)作为单独的部分并且没有超过26，后续有多少种方法
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                // (i和i+1)作为单独的部分，后续有多少种方法
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] == '3' ~ '9'
        return process(str, i + 1);
    }

    public static int dpWays(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < n) {
                    dp[i] += dp[i + 2];
                }
            } else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("11111"));
        System.out.println(dpWays("11111"));
    }

}


