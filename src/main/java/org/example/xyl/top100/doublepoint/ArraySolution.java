package org.example.xyl.top100.doublepoint;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2022/9/2
 */
public class ArraySolution {


    /**
     * 下一个排列
     * 必须 原地 修改，只允许使用额外常数空间
     *
     * @param nums 1 <= nums.length <= 100
     *             0 <= nums[i] <= 100
     */
    public void nextPermutation(int[] nums) {

        if (nums.length == 1) {
            return;
        }
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                Arrays.sort(nums, i, n);
                for (int j = i; j < n; j++) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, j, i - 1);
                        return;
                    }
                }
            }
        }

        //最大排列时  直接倒序
        Arrays.sort(nums);
    }


    /**
     * 交换
     */
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     * 数组倒序
     */
    public void reverse(int[] array) {
        int length = array.length;
        int temp;
        for (int i = 0; i < length / 2; i++) {
            temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
    }


    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
//        int[] nums = {1, 2, 3};
//        int[] nums = {2, 3, 1};
//        int[] nums = {3, 2, 1};
        int[] nums = {1, 3, 2};
//        Arrays.sort(nums, 1, 3);
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

    }
}
