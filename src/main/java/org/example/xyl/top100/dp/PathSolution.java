package org.example.xyl.top100.dp;

/**
 * @author xiangyanlin
 * @date 2022/12/6
 */
public class PathSolution {

    int m, n;

    /**
     * 机器人运动路径
     * 暴力递归
     *
     * @param m 1 <= m, n <= 100
     * @param n 1 <= m, n <= 100
     * @return 题目数据保证答案小于等于 2 * 109
     */
    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
        return paths(0, 0) + 1;
    }

    public int paths(int mIndex, int nIndex) {
        if (mIndex < m - 1 && nIndex < n - 1) {
            return paths(mIndex + 1, nIndex) + paths(mIndex, nIndex + 1) + 1;
        }

        if (mIndex >= m - 1 && nIndex < n - 1) {
            return paths(mIndex, nIndex + 1);
        }

        if (mIndex < m - 1 && nIndex >= n - 1) {
            return paths(mIndex + 1, nIndex);
        }
        return 0;
    }


    /**
     * 机器人运动路径
     * 动态规划
     *
     * @param m 1 <= m, n <= 100
     * @param n 1 <= m, n <= 100
     * @return 题目数据保证答案小于等于 2 * 109
     */
    public int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];
        //边界只能为1
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        //边界没有选择
        for (int i = 1; i < n; i++) {
            grid[0][i] = grid[0][i] + grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] = grid[i][0] + grid[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        PathSolution solution = new PathSolution();
//        System.out.println(solution.uniquePathsDP(3, 3));

        int[][] grip = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println(solution.minPathSum(grip));
    }
}
