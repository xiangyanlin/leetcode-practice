package org.example.xyl.hw;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 火锅问题
 *
 * @author xiangyanlin
 * @date 2023/3/24
 */
public class Main4 {


    //第一行两个整数n，m
    //其中n代表往锅里下菜的个数
    //m代表手速

    //接下来有n行，
    //每行有两个数x，y
    //代表第x秒下的菜过y秒才能变得刚好合适（1 < mn < 1000），（1 < xy < 1000）

    //输出一个整数代表用最合理的策略，最多能吃到刚好合适的菜的数量

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            time[i] = x + y;
        }

        Arrays.sort(time);

        int[] dp = new int[n];

        int next = 0;
        //第一颗菜 夹上
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            //(吃上一颗菜 + 手速) <= 第i颗菜熟的时间  能吃上
            if ((time[next] + m) <= time[i]) {
                dp[i] = 1;
                next = i;
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
