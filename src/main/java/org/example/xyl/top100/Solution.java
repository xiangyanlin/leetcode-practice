package org.example.xyl.top100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyanlin
 * @date 2022/8/10
 */
public class Solution {

    /**
     * 两数之和
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     */
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        //如何保存相同的值
        Map<Integer, Integer> map = new HashMap<>(length);
        Map<Integer, Integer> map1 = new HashMap<>(length);

        //建立索引
        for (int i = 0; i < length; i++) {
            if(map.containsKey(nums[i])) {
                map1.put(nums[i], i);
                continue;
            }
            map.put(nums[i], i);
        }
        int l = 0, r = length - 1;
        Arrays.sort(nums);
        while (l < r) {
            if(nums[l] + nums[r] == target) {
                if(nums[l] == nums[r]) {
                    return new int[]{map.get(nums[l]), map1.get(nums[r])};
                }
                return new int[]{map.get(nums[l]), map.get(nums[r])};
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

    /**
     * 官方解法
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = solution.twoSum(new int[]{2, 7, 11, 5}, 9);
        System.out.println(Arrays.toString(arr));
    }
}
