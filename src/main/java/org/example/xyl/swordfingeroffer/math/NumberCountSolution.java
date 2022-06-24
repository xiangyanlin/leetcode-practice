package org.example.xyl.swordfingeroffer.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyanlin
 * @date 2022/6/24
 */
public class NumberCountSolution {

    //----------------- 剑指 Offer 39. 数组中出现次数超过一半的数字----------

    public int majorityElement(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int m = nums.length / 2;

        //统计每个数出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : nums) {
            int count = 1;
            if (map.containsKey(number)) {
                count = map.get(number) + 1;
            }
            if (count > m) {
                return number;
            }
            map.put(number, count);
        }
        return 0;
    }


    //------------------ 剑指 Offer 66. 构建乘积数组 ------------------------


    public int[] constructArr(int[] a) {
        if (a == null) {
            return null;
        }
        int length = a.length;
        int[] pre = new int[length], out = new int[length], b = new int[length];
        for (int i = 0; i < length; i++) {
            //构建给个元素的前缀积  和每个元素的后缀积
            //前缀 0 到 i - 1;
            if (i == 0) {
                pre[i] = 1;
            } else {
                pre[i] = pre[i - 1] * a[i - 1];
            }
        }
        for (int i = length - 1; i >= 0; i--) {
            //后缀 i+1 到 r
            if (i == length - 1) {
                out[i] = 1;
            } else {
                out[i] = out[i + 1] * a[i + 1];
            }
        }
        for (int i = 0; i < length; i++) {
            b[i] = pre[i] * out[i];
        }
        return b;
    }

    public int[] productExceptSelf(int[] a) {
        int length = a.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = a[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= a[i];
        }
        return answer;
    }


    public static void main(String[] args) {
        NumberCountSolution solution = new NumberCountSolution();

        int[] b = solution.constructArr(new int[]{1, 2, 3, 4, 5});
        Arrays.stream(b).forEach(System.out::println);
    }
}
