package org.example.xyl.swordfingeroffer.sort;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 * @author xiangyanlin
 * @date 2022/6/16
 */
public class LeastNumbersSolution {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        if(arr == null || k <= 0) {
            return result;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int number : arr) {
            heap.add(number);
        }

        for (int i = 0; i < k; i++) {
            if(!heap.isEmpty()) {
                result[i] = heap.poll();
            }
        }
        return result;
    }
}
