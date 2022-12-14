package org.example.xyl.swordfingeroffer.recur;


/**
 * 玩家A和玩家B依次拿走每张纸牌
 * 范围上尝试的模型
 *
 * @author xiangyanlin
 * @date 2022/7/10
 */
public class CardsInLine {

	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}

	public static int f(int[] arr, int i, int j) {
		if (i == j) {
			return arr[i];
		}
		return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
	}

	public static int s(int[] arr, int i, int j) {
		if (i == j) {
			return 0;
		}
		return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
	}

	/**
	 * 改动态规划
	 */
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int[][] f = new int[n][n];
		int[][] s = new int[n][n];
		for (int j = 0; j < n; j++) {
			//f对角线
			f[j][j] = arr[j];
			for (int i = j - 1; i >= 0; i--) {
				f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
				s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
			}
		}
		return Math.max(f[0][n - 1], s[0][n - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 1, 9, 1 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));

	}

}
