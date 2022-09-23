package org.example.xyl.top100.math;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2022/9/23
 */
public class RotateSolution {


    /**
     * 旋转图像
     *
     * @param matrix n == matrix.length == matrix[i].length
     *               1 <= n <= 20
     *               -1000 <= matrix[i][j] <= 1000
     */
    public void rotate(int[][] matrix) {
        // n * n矩阵
        int n = matrix.length;
        int[][] snapshot = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                snapshot[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            //将第一行变成最后一列
            int col = n - i - 1;
            for (int j = 0; j < n; j++) {
                int cur = snapshot[i][j];
                matrix[j][col] = cur;
            }
        }

    }

    /**
     * 官方答案 不使用中间矩阵
     *
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 官方答案 用翻转代替旋转
     *
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                //swap
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }



    public static void main(String[] args) {
        RotateSolution solution = new RotateSolution();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        solution.rotate(matrix);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }
}
