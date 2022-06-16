package org.example.xyl.swordfingeroffer.sort;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 *
 * @author xiangyanlin
 * @date 2022/6/16
 */
public class IsStraightSolution {
    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        //1统计0的个数
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            }
        }
        //2 快排
        quickSort(nums);
        //判断相差值
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == 0 ) {
                continue;
            }
            if ( nums[i + 1] == nums[i]) {
                return false;
            }
            if (nums[i + 1] - nums[i] - 1 > count) {
                return false;
            }
        }
        return true;
    }
    public void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int l = 0;
        int r = nums.length - 1;
        quickSort(nums, l, r);

    }
    public void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        //取随机数
        int base = l + (int) (Math.random() * (r - l + 1));
        //和最后一位交换
        swap(nums, base, r);
        int m = partition(nums, l, r);
        quickSort(nums, l, m - 1);
        quickSort(nums, m + 1, r);

    }

    public int partition(int[] nums, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        //小于置边界
        int less = l - 1;
        while (l < r) {
            if (nums[l] <= nums[r]) {
                swap(nums, ++less, l);
            }
            l++;
        }
        swap(nums, ++less, r);
        return less;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        IsStraightSolution solution = new IsStraightSolution();
//        int[] nums = {0 ,0, 1, 2, 5};
//        System.out.println(solution.isStraight(nums));
//        int[] nums1 = {1 ,2, 3, 4, 5};
        int[] nums1 = {0 ,0, 8, 5, 4};
        for (int i = 0; i < 1000; i++) {
            System.out.println(solution.isStraight(nums1));
        }
    }
}
