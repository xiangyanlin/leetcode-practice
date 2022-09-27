package org.example.xyl.top100.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyanlin
 * @date 2022/9/27
 */
public class JumpSolution {

    Map<Integer, Boolean> map = new HashMap<>(16);

    /**
     * 跳跃游戏
     *
     * @param nums 1 <= nums.length <= 3 * 104
     *             0 <= nums[i] <= 105
     * @return 是否能够到达最后一个下标。
     */

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return nums[0] >= 0;
        }

        return process(nums, 0);
    }

    public boolean process(int[] nums, int index) {
        //base case
        if (index >= nums.length - 1) {
            return true;
        }
        int num = nums[index];
        for (int i = 1; i <= num; i++) {
            boolean flag;
            if (map.containsKey(index + i)) {
                flag = map.get(index + i);
            } else {
                flag = process(nums, index + i);
                map.put(index, flag);
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }


    /**
     * 贪心
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }





    public static void main(String[] args) {
        JumpSolution solution = new JumpSolution();
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(solution.canJump2(nums));
    }
}
