package org.example.xyl.swordfingeroffer.dp;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2022/7/20
 */
public class DpHardSolution {

    //----------------剑指offer46 丑数---------------

    public int nthUglyNumber1(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }

    //---------------剑指offer60 n个骰子的点数----------------


    /**
     * 1 <= n <= 11
     * 暴力法：遍历所有的点数组合
     * 遍历n个筛子  每个筛子的点数
     */
    double[] pointCont;
    int n;

    public double[] dicesProbability(int n) {

        if (n == 1) {
            double v = 1.0 / 6;
            return new double[]{v, v, v, v, v, v};
        }

        //总点数
        int length = 5 * n + 1;

        //点数从 n 到 6n
        double[] probability = new double[length];

        pointCont = new double[length];
        this.n = n;

        double maxCount = Math.pow(6, n);

        //可变参数 n 点数x 点数数量p   求概率
        process(0, 1, 1);
        process(0, 1, 2);
        process(0, 1, 3);
        process(0, 1, 4);
        process(0, 1, 5);
        process(0, 1, 6);
        for (int i = 0; i < length; i++) {
            probability[i] = pointCont[i]  / maxCount;
        }
        return probability;
    }


    /**
     * 暴力递归
     *
     * @param prePoint 已经决策的点数
     * @param point    当前筛子的点数
     * @param index    决策点
     */
    public void process(int prePoint, int index, int point) {
        int cur = prePoint + point;
        if (index == n) {
            pointCont[cur - n] += 1;
            return;
        }
        //当前总点数为prePoint + point

        process(cur, index + 1, 1);
        process(cur, index + 1, 2);
        process(cur, index + 1, 3);
        process(cur, index + 1, 4);
        process(cur, index + 1, 5);
        process(cur, index + 1, 6);
        //  0 1 6  6 2 6  12 3 6 18 4 6

    }


    /**
     * 动态规划
     */
    public double[] dicesProbability1(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];

            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    //j + k相同的会做概率累加
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }


    //-----------------------剑指offer19 正则表达式匹配 --------------------------------------


    String s, p;

    /**
     * '*'表示它前面的字符可以出现任意次（含0次）
     * 模式中的字符'.'表示任意一个字符
     * @param p  可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
     */
    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        int m = s.length() , n = p.length() ;
        return isMatchProcess(m, n);
    }

    /**
     * todo test 边界值
     * 暴力递归
     * @param i s的第i个字符
     * @param j p的第j个字符
     * @return s是否匹配
     */
    public boolean isMatchProcess(int i, int j) {

        //base case
        if (i == 0 && j == 0) {
            return true;
        } else if (i == 0) {
            return ( j == 1 || isMatchProcess(0, j - 2))
                    && p.charAt(j - 1) == '*';
        }

        if (p.charAt(j - 1) == '*') {
            //p[j]能匹配s[i -1]

            if ( j == 1) {
                return true;
            }
            if (i == 1) {
                return isMatchProcess(i, j - 1) || isMatchProcess(0, j);
            }

            return
                    //p的上一个能匹配
                    isMatchProcess(i, j - 1)
                            ||
                            //p能匹配s[i -1]
                            (isMatchProcess(i - 1, j)
                                    && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
        } else {
            //p[j - 1] 能匹配 s[i -1] 且 （p[j ] 为 '.'|| p[j ] ==  s[i ]  ）
            return isMatchProcess(i - 1, j - 1)
                    && (p.charAt(j - 1) == '.'
                    || s.charAt(i - 1) == p.charAt(j - 1));
        }
    }


    public boolean isMatch1(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        //默认值 false
        boolean[][] dp = new boolean[m][n];
        //base case  s p都是空
        dp[0][0] = true;
        //s为空时 p只能为空或*
        for (int j = 2; j < n; j += 2) {
            dp[0][j] =
                    dp[0][j - 2]
                            && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] =
                        p.charAt(j - 1) == '*'
                                ?
                                //p[j]能匹配s[i -1]
                                dp[i][j - 2] ||
                                        dp[i - 1][j]
                                                && (s.charAt(i - 1) == p.charAt(j - 2)
                                                || p.charAt(j - 2) == '.')
                                :
                                //p[j - 1] 能匹配 s[i -1] 且 （p[j ] 为 '.'|| p[j ] ==  s[i ]  ）
                                dp[i - 1][j - 1]
                                        && (p.charAt(j - 1) == '.'
                                        || s.charAt(i - 1) == p.charAt(j - 1));
            }
        }
        return dp[m - 1][n - 1];
    }


    public boolean isMatch2(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for(int j = 2; j < n; j += 2) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        // 状态转移
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(p.charAt(j - 1) == '*') {
                    if(dp[i][j - 2]) {
                        dp[i][j] = true;                                            // 1.
                    } else if(dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) {
                        dp[i][j] = true; // 2.
                    } else if(dp[i - 1][j] && p.charAt(j - 2) == '.') {
                        dp[i][j] = true;             // 3.
                    }
                } else {
                    if(dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) {
                        dp[i][j] = true;  // 1.
                    } else if(dp[i - 1][j - 1] && p.charAt(j - 1) == '.') {
                        dp[i][j] = true;         // 2.
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }




    public static void main(String[] args) {
        DpHardSolution solution = new DpHardSolution();
//        System.out.println(Arrays.toString(solution.dicesProbability(3)));
//        System.out.println(Arrays.toString(solution.dicesProbability1(3)));
//        System.out.println(solution.dicesProbability1(2).length);
        //046296296296296294
//        System.out.println(0.004629629629629629 * 9);
//        System.out.println(solution.isMatch("", " *"));
        System.out.println(solution.isMatch2("", "***"));
//       boolean[] arr = new boolean[1];
//        System.out.println(arr[0]);
    }
}
