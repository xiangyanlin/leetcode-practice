package org.example.xyl.swordfingeroffer.sort;

/**
 * @author xiangyanlin
 * @date 2022/7/14
 */
class Solution {
    int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }
    private int mergeSort(int l, int r) {
        // 终止条件
        if (l >= r) {
            return 0;
        }
        // 递归划分
        int m = (l + r) / 2;
        int res = mergeSort(l, m) + mergeSort(m + 1, r);
        // 合并阶段
        int i = l, j = m + 1;
        if (r + 1 - l >= 0) {
            System.arraycopy(nums, l, tmp, l, r + 1 - l);
        }
        //排序与统计  是的nums 从 l 到 r有序
        for (int k = l; k <= r; k++) {
            if (i == m + 1) {
                //左边已经排完。 右边直接全部拿过来
                nums[k] = tmp[j++];
            } else if (j == r + 1 || tmp[i] <= tmp[j]) {
                //右边已经全部排完 或者 左边的值小于右边  吧左边的值放过去
                nums[k] = tmp[i++];
            } else {
                //左右都没有排完  并且 左边大于右边
                nums[k] = tmp[j++];
                // 统计逆序对 i 以及 i到 m 都大于 右边  都组成一个逆序对
                res += m - i + 1;
            }
        }
        return res;
    }
}

