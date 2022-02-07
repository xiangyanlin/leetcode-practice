package org.example.xyl.swordfingeroffer;

import java.util.*;

/**
 * 查找书案发
 */
public class SearchSolution {

    /**
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap(16);
        if (nums.length <= 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return nums[i];
            } else {
                map.put(nums[i], nums[i]);
            }
        }
        return 0;
    }

    //---------------------------------------------------------------------

    /**
     * 统计一个数字在排序数组中出现的次数。
     */
    public int search(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap(16);
        if (nums.length <= 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        if (!map.containsKey(target)) {
            return 0;
        }
        return map.get(target);
    }

    /**
     * 二分法
     */
    public int search1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * ～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        //找出一个数字
        return nums.length;
    }

    /**
     * 0 1 2 3 5
     * 0 2 3 4 5
     * 二分法
     */
    public int missingNumber1(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
    }






    public static char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        int a[] = {1, 10, 10, 10};
        System.out.println(minArray(a));
    }

    public static int minArray(int[] numbers) {
        int i = 0, j = numbers.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            //和最右边的元素比较
            if (numbers[m] < numbers[j] ) {
                //后半部分有序。前面找
                j = m;
            } else if (numbers[m] > numbers[j]) {
                //前半部分有序。去后面找
                //选择右边
                i = m + 1;
            } else {
                //相等时。最右指针左移一位
                j = j - 1;
            }
        }
        return numbers[i];
    }


    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int row = 0;
        int column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }




}
