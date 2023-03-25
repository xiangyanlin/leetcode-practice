package org.example.xyl.hw.examin.three;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author xiangyanlin
 * @date 2023/3/18
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        int m = in.nextInt();


        List<Integer> list = Arrays.stream(s.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int min = list.stream().min(Integer::compareTo).get();

        int res = 0;
        if (m > 0) {
            res = process(list, m, min);
        }
        System.out.println(res);
    }

    public static int process(List<Integer> list, int rest, int min) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (rest < min) {
            return 1;
        }
        int res = 0;
        for (Integer choice : list) {
            res += process(list, rest - choice, min);
        }
        return res;
    }

    public static int dp(List<Integer> list, int rest, int min) {
        int[] dp = new int[rest];
        dp[0] = 1;
        for (int i = 0; i <= min; i++) {
            dp[i] = 1;
        }

        //rest
        for (int i = min + 1; i < rest; i++) {
            //choice
            for (Integer choice : list) {
                if (i - choice > 0) {
                    dp[i] += dp[i - choice];
                }

            }
        }
        return dp[rest - 1];
    }
}
