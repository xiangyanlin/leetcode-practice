package org.example.xyl.top100.greedy;

/**
 * @author xiangyanlin
 * @date 2022/8/23
 */
public class WaterSolution {


    //11盛最多的水

    int maxWater;
    int[] height;

    /**
     * 盛最多水的容器
     *
     * @param height n == height.length
     *               2 <= n <= 105
     *               0 <= height[i] <= 104
     * @return 容器可以储存的最大水量
     */

    public int maxArea1(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    public int maxArea2(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                res = Math.max(res, (j - i) * height[i]);
                i++;
            } else {
                res = Math.max(res, (j - i) * height[j]);
                j--;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        WaterSolution solution = new WaterSolution();
        int[] height = {1,8,6,2,5,4,8,3,7};
        int max = solution.maxArea2(height);
        System.out.println(max);
    }
}
