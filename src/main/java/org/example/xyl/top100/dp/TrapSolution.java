package org.example.xyl.top100.dp;

/**
 * @author xiangyanlin
 * @date 2022/9/21
 */
public class TrapSolution {

    /**
     * 接雨水
     *
     * @param height n == height.length
     *               1 <= n <= 2 * 104
     *               0 <= height[i] <= 105
     * @return 雨水数量
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;

    }



    public static void main(String[] args) {
        TrapSolution trapSolution = new TrapSolution();
        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(trapSolution.trap(height));
    }
}
