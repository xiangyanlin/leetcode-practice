package org.example.xyl.top100.string.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiangyanlin
 * @date 2023/2/22
 */
public class OrangesSolution {


    /**
     * 腐烂的橘子
     *
     * @param grid m == grid.length
     *             n == grid[i].length
     *             1 <= m, n <= 10
     *             grid[i][j] 仅为 0、1 或 2
     *             值 0 代表空单元格；
     *             值 1 代表新鲜橘子；
     *             值 2 代表腐烂的橘子
     * @return 最小分钟
     */
    public int orangesRotting(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return -1;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        //int[] 存储r,c坐标 存储腐烂的橘子
        Queue<int[]> queue = new LinkedList<>();
        // count 表示新鲜橘子的数量
        int count = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == 1) {
                    count++;
                } else if (grid[r][c] == 2) {
                    //0分钟时的腐烂橘子
                    queue.add(new int[]{r, c});
                }
            }
        }
        // round 表示腐烂的轮数，或者分钟数
        int round = 0;
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int n = queue.size();

            //所有新鲜橘子同时扩散
            for (int i = 0; i < n; i++) {
                int[] orange = queue.poll();
                int r = orange[0];
                int c = orange[1];
                //四个方向 如果有新鲜橘子 则腐烂
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    //腐烂
                    grid[r - 1][c] = 2;
                    count--;
                    queue.add(new int[]{r - 1, c});
                }
                if (r + 1 < nr && grid[r + 1][c] == 1) {
                    //腐烂
                    grid[r + 1][c] = 2;
                    count--;
                    queue.add(new int[]{r + 1, c});
                }
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    //腐烂
                    grid[r][c - 1] = 2;
                    count--;
                    queue.add(new int[]{r, c - 1});
                }
                if (c + 1 < nc && grid[r][c + 1] == 1) {
                    //腐烂
                    grid[r][c + 1] = 2;
                    count--;
                    queue.add(new int[]{r, c + 1});
                }
            }
        }

        if (count > 0) {
            return -1;
        } else {
            return round;
        }

    }
}
