package org.example.xyl.top100.search;

import java.util.Arrays;

/**
 * @author xiangyanlin
 * @date 2022/8/18
 */
public class SearchSolution {


    /**
     * 寻找两个正序数组的中位数
     * 算法的时间复杂度应该为 O(log (m+n))
     *
     * @param nums1 nums1.length == m  0 <= m <= 1000
     * @param nums2 nums2.length == n  0 <= n <= 1000 1 <= m + n <= 2000
     *              -106 <= nums1[i], nums2[i] <= 106
     * @return 中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        if (nums1.length == 0) {
            if (nums2.length % 2 == 0) {
                return (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0;
            }
            return nums2[nums2.length / 2];
        }
        if (nums2.length == 0) {
            if (nums1.length % 2 == 0) {
                return (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0;
            }
            return nums1[nums1.length / 2];
        }
        int[] merge = merge(nums1, nums2);
        if (merge.length % 2 == 0) {
            return (merge[merge.length / 2 - 1] + merge[merge.length / 2]) / 2.0;
        }
        return merge[merge.length / 2];
    }


    public int[] merge(int[] nums1, int[] nums2) {
        int i = 0, j = 0, k = 0;
        int[] num = new int[nums1.length + nums2.length];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                num[k++] = nums1[i];
                i++;
            } else {
                num[k++] = nums2[j];
                j++;
            }

        }
        for (int l = i; l < nums1.length; l++) {
            num[k++] = nums1[i];
            i++;
        }
        for (int l = j; l < nums2.length; l++) {
            num[k++] = nums2[j];
            j++;
        }
        return num;
    }

    /**
     * 官方答案
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


    /**
     * 搜索旋转排序数组
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *
     * @param nums   1 <= nums.length <= 5000
     *               -104 <= nums[i] <= 104
     *               nums 中的每个值都 独一无二
     *               题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * @param target -104 <= target <= 104
     * @return 下标
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //左边有序
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            //左边无序 右边有序
            else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        SearchSolution solution = new SearchSolution();
        int[] nums1 = {1, 2}, nums2 = {3, 4};
        double medianSortedArrays = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }
}
