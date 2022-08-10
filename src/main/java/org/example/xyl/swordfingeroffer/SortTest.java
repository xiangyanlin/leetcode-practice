package org.example.xyl.swordfingeroffer;

import java.util.Arrays;

/**
 * 排序算法
 * @author xiangyanlin
 * @date 2022/4/4
 */
public class SortTest {

    public static void main(String[] args) {
        SortTest test = new SortTest();
        int[] arr = {2,1,4,3};
        test.insertSort(arr);

    }

    /**
     * 插入排序
     */
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i ; j>0 && arr[j-1] > arr[j]; j--) {
                swap(arr, j-1, j);
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] =arr[j];
        arr[j] = temp;

    }
}
