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

    /**
     * 1 取随机数 和最后一位交换 作为基准元素
     * 2 比基准元素大的元素移动到基准元素的右侧，比基准元素小的移动到作左侧
     * 3 对 基准袁术的前后 递归重复此过程
     */
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
                //<= 未做交换
                //>= 将大于的元素放在当前元素之后
                swap(nums, ++less, l);
            }
            l++;
        }
        // (<=) (<=) (<=) (<=) (>) (>) (>) (基准)
        swap(nums, ++less, r);
        // (<=) (<=) (<=) (<=)  (基准) (>) (>) (>)
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
