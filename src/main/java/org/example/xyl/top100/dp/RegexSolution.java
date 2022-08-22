package org.example.xyl.top100.dp;

/**
 * @author xiangyanlin
 * @date 2022/8/22
 */
public class RegexSolution {


    /**
     * 正则表达式匹配
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     *
     * @param s 1 <= s.length <= 20 s 只包含从 a-z 的小写字母。
     * @param p 1 <= p.length <= 30 p 只包含从 a-z 的小写字母，以及字符 . 和 *
     * @return 是否匹配
     */
    public boolean isMatch(String s, String p) {
        //变化范围
        int m = s.length() + 1, n = p.length() + 1;

        //状态存储
        boolean dp[][] = new boolean[m][n];

        //base case s p 都为空(空指的是没有字符 不是 空格)
        dp[0][0] = true;

        // 初始化首行 s为空时 p只能为*
        //下标为奇数是是false ‘*’ 只能让前一个为零
        for (int i = 2; i < n; i += 2) {
            //s为空时 p只能为空或*
            if (dp[0][i - 2] && p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            }
        }

        //状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //当前字符是否 ‘*’
                if (p.charAt(j - 1) == '*') {
                    //往前两个是true则为true
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if (dp[i - 1][j]
                            && s.charAt(i - 1) == p.charAt(j - 2)) {
                        //p-2不能匹配s p匹配 s-1  s == p-1
                        dp[i][j] = true;
                    } else if (dp[i - 1][j]
                            && p.charAt(j - 2) == '.') {
                        //p-2不能匹配s p匹配 s-1  s == p-1 ('.' 匹配任意单个字符)
                        dp[i][j] = true;
                    }
                } else {
                    //当前不是'*' 则 p-1能匹配s-1
                    if(dp[i - 1][j - 1]
                            && s.charAt(i - 1) == p.charAt(j - 1)) {
                        // p-1能匹配s-1  且s==p(当前字符相等)
                        dp[i][j] = true;
                    } else if(dp[i - 1][j - 1]
                            && p.charAt(j - 1) == '.') {
                        // p-1能匹配s-1  且s==p(当前字符相等 '.' 匹配任意单个字符)
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        RegexSolution regex = new RegexSolution();
        String s = "", p = "s1*";
        boolean match = regex.isMatch(s, p);
        System.out.println(match);
    }
}
