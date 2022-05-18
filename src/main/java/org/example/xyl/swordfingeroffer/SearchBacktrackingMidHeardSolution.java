package org.example.xyl.swordfingeroffer;

/**
 * 搜索与回溯算法（中等）
 *
 * @author xiangyanlin
 * @date 2022/5/14
 */
public class SearchBacktrackingMidHeardSolution {

    /**
     * 矩阵路径
     */
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        }
        if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {
                //i + 0, j + 1 下移动
                {0, 1},
                //i + 0, j - 1 上移动
                {0, -1},
                //i + 1, j  右移动
                {1, 0},
                //i - 1, j  左移动
                {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];

            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }


    public static void main(String[] args) {
        SearchBacktrackingMidHeardSolution test = new SearchBacktrackingMidHeardSolution();
        char board[][] = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'}
                , {'A', 'D', 'E', 'E'}};
        System.out.println(test.exist(new char[][]{{'a'}}, "b"));
    }
}
