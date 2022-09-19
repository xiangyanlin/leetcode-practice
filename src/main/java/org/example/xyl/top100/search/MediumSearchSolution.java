package org.example.xyl.top100.search;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2022/9/19
 */
public class MediumSearchSolution {

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * @param nums   0 <= nums.length <= 105
     *               -109 <= nums[i] <= 109
     *               nums 是一个非递减数组
     * @param target -109 <= target <= 109
     * @return 排序数组中查找元素的第一个和最后一个位置
     */
    public int[] searchRange(int[] nums, int target) {
        //nums递增
        int start = -1, end = -1;
        if (nums == null || nums.length == 0) {
            return new int[]{start, end};
        }
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                int startIndex = mid;
                while (startIndex > 0) {
                    if (nums[startIndex - 1] != target) {
                        break;
                    }
                    startIndex--;
                }
                start = startIndex;

                int endIndex = mid;
                while (endIndex < r) {
                    if (nums[endIndex + 1] != target) {
                        break;
                    }
                    endIndex++;
                }
                end = endIndex;

                if (start != -1 && end != -1) {
                    break;
                }

            } else if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return new int[]{start, end};
    }


    public static void main(String[] args) {
        MediumSearchSolution solution = new MediumSearchSolution();
        int[] nums = new int[]{1,2,3,3,3,3,4,5,9};
        int target = 3;
        int[] r = solution.searchRange(nums, target);
        System.out.println(Arrays.toString(r));
    }
}
