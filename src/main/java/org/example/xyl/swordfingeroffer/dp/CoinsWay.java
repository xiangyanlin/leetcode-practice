package org.example.xyl.swordfingeroffer.dp;

/**
 * 不同面值组合
 *
 * @author xiangyanlin
 * @date 2022/7/20
 */
public class CoinsWay {

    /**
     * @param arr arr中都是正数且无重复值，
     * @return 返回组成aim的方法数
     */
    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    /**
     * 如果自由使用arr[index...]的面值，组成rest这么多钱，返回方法数 （1 , 6）
     */
    public static int process(int[] arr, int index, int rest) {
        // 无面值的时候
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // 有面值的时候
        int ways = 0;
        // arr[index] 当钱面值
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }

    public static int waysdp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        // 大顺序 从下往上
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int sum = 350;
        System.out.println(ways(arr, sum));
        System.out.println(waysdp(arr, sum));
    }

}
