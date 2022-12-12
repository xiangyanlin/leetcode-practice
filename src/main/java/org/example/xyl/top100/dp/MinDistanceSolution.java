package org.example.xyl.top100.dp;

/**
 * @author xiangyanlin
 * @date 2022/12/12
 */
public class MinDistanceSolution {


    /**
     * 72编辑距离
     *
     * @param word1 0 <= word1.length, word2.length <= 500
     * @param word2 word1 和 word2 由小写英文字母组成
     * @return 最少操作次数
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] dp = new int[m + 1][n + 1];

        // 边界状态初始化
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int leftDown = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    leftDown += 1;
                }
                dp[i][j] = Math.min(left, Math.min(down, leftDown));
            }
        }
        return dp[m][n];
    }


    String word1, word2;

    public int minDistance1(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        this.word1 = word1;
        this.word2 = word2;

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }
        return process(m, n);
    }

    /**
     * A 的前 i 个字母和 B 的前 i 个字母之间的编辑距离
     * 假设我们只操作 word1 word2末尾
     *
     * @param i A 的前 i 个字母
     * @param j B 的前 i 个字母
     * @return 编辑距离
     */
    public int process(int i, int j) {

        if (i * j == 0) {
            return i + j;
        }
        //三种情况

        //新增word1
        int one = process(i - 1, j) + 1;

        //新增word2
        int two = process(i, j - 1) + 1;

        //编辑word3  是最后一个字符相等
        int three = process(i - 1, j - 1);
        if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
            three += 1;
        }

        //取三种情况下最小值
        return Math.min(Math.min(one, two), three);
    }

    public static void main(String[] args) {
        MinDistanceSolution solution = new MinDistanceSolution();
        System.out.println(solution.minDistance1("horse", "ros"));
        System.out.println(solution.minDistance("horse", "ros"));

        System.out.println(solution.minDistance1("intention", "execution"));
        System.out.println(solution.minDistance("intention", "execution"));
    }
}
