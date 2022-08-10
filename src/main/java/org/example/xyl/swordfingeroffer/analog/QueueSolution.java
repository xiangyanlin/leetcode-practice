package org.example.xyl.swordfingeroffer.analog;

/**
 * @author xiangyanlin
 * @date 2022/7/11
 */
public class QueueSolution {

    //---------------------剑指 Offer 59 - I. 滑动窗口的最大值-------------------------

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) {
            return new int[]{};
        }
        int index = 0;
        int[] r = new int[nums.length - (k - 1)];
        MaxQueue queue = new MaxQueue();
        for (int i = 0; i < nums.length; i++) {

            if (i >= k ) {
                r[index++] = queue.max_value();
                queue.pop_front();
            }
            queue.push_back(nums[i]);
        }
        r[index] = queue.max_value();
        return r;
    }

    public static void main(String[] args) {
        QueueSolution solution = new QueueSolution();
        int[] r = solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int i : r) {
            System.out.println(i);
        }
    }

}
