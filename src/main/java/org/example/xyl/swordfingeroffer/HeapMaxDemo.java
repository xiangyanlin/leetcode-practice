package org.example.xyl.swordfingeroffer;

import java.util.PriorityQueue;

/**
 * 最大堆
 *
 * @author xiangyanlin
 * @date 2022/5/4
 */
public class HeapMaxDemo {

    public int[] value = {};

    /**
     * 数组的位置
     */
    int heapSize = 0;

    public HeapMaxDemo(int[] value) {
        this.value = value;
        // O(n)
        for (int i = 0; i < value.length; i++) {
            heapSize++;
           // O(logN)
            heapInsert(i);
        }
    }

    public HeapMaxDemo() {
    }

    public int getTop() {
        return value[0];
    }

    public int getAndRemoveTop() {
        int r = value[0];
        heapify(heapSize);
        swap(value, 0, --heapSize);
        return r;

    }

    public void add(int val) {
        value[heapSize] = val;
        heapInsert(heapSize);
        heapSize++;

    }


    public void heapInsert(int cur) {
        int parent = getParentIndex(cur);
        while (value[cur] > value[parent]) {
            swap(value, cur, parent);
            cur = (cur - 1) / 2;
            parent = getParentIndex(cur);
        }

    }

    public void heapify(int cur) {
        int left = getLeftChildIndex(cur);
        while (left < heapSize) {
            Integer parentIndex = getParentIndex(cur);
            //最大孩子
            int largest = left + 1 < heapSize && value[left + 1] > value[left]
                    ? left + 1 : left;

            largest = value[largest] > value[parentIndex]
                    ? largest : parentIndex;

            if (largest == cur) {
                break;
            }

            swap(value, largest, parentIndex);
            cur = largest;
            left = getLeftChildIndex(left);

        }

    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    public Integer getParentIndex(int cur) {
        return (cur - 1) / 2;
    }

    public Integer getLeftChildIndex(int cur) {
        return 2 * cur + 1;
    }

    public Integer getRightChildIndex(int cur) {
        return 2 * cur + 2;
    }

    public static void main(String[] args) {
        int arr[] = {2, 1, 3, 5, 4};
        HeapMaxDemo demo = new HeapMaxDemo(arr);
        //堆排序
        demo.swap(demo.value, 0, --demo.heapSize);
        while (demo.heapSize > 0) {
            demo.heapify(0);
            demo.swap(demo.value, 0, --demo.heapSize);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        System.out.println(demo.getTop());
    }

}
