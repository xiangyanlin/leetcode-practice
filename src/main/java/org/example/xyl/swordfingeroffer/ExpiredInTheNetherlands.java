package org.example.xyl.swordfingeroffer;

/**
 * 荷兰国旗问题
 * @author xiangyanlin
 * @date 2022/4/12
 */
public class ExpiredInTheNetherlands {


    public int[] leftAndRight(int[] arr, int num) {
        int order = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] <= num) {
                swap(arr, order, i);
                order++;
            }
        }
        return arr;
    }

    public int[] leftMidRight(int[]arr , int num) {
        int l = 0;
        int r = arr.length;
        int i = 0;
        while (i != r) {
            if(arr[i] == num) {
                i++;
            } else if(arr[i] < num) {
                swap(arr, l, i);
                l++;
                i++;
            } else {
                swap(arr, r-1, i);
                r--;
            }
        }
        return arr;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] =arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        ExpiredInTheNetherlands test = new ExpiredInTheNetherlands();
        int[] arr = {3, 4,2, 5, 7, 4, 3};
        int[] ints = test.leftMidRight(arr, 3);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
