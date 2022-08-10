package org.example.xyl.swordfingeroffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xiangyanlin
 * @date 2022/5/4
 */
public class HeapForPriorityQueue {

    public void sortDistanceLessK(int arr[], int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(k, arr.length); index++) {
            minHeap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            minHeap.add(arr[index]);
            arr[i] = minHeap.poll();
        }
        while (!minHeap.isEmpty()) {
            arr[i++] = minHeap.poll();
        }
    }


    /**
     *  sort
     */
    public static void main(String[] args) {
        int arr[] = {1, 7, 4, 2, 4, 3, 5};
        HeapForPriorityQueue test = new HeapForPriorityQueue();
        test.sortDistanceLessK(arr, 3);
        Arrays.stream(arr).forEach(System.out::println);

    }
}
