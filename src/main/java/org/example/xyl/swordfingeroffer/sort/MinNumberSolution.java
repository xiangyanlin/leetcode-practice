package org.example.xyl.swordfingeroffer.sort;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 *
 * @author xiangyanlin
 * @date 2022/6/13
 */
public class MinNumberSolution {

    public String minNumber(int[] nums) {
        StringBuilder res = new StringBuilder();
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        quickSort(strs);
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();

    }

    public void quickSort(String[] str) {
        if (str == null || str.length < 2) {
            return;
        }
        int l = 0;
        int r = str.length - 1;
        quickSort(str, l, r);
    }

    public void quickSort(String[] str, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(str, L, R);
        quickSort(str, L, M - 1);
        quickSort(str, M + 1, R);
    }

    public void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int partition(String[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if ((arr[index] + arr[R]).compareTo(arr[R] + arr[index])<=0) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }



    public void sort(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if ((strs[i] + strs[j]).compareTo(strs[j] + strs[i]) >= 0) {
                    swap(strs, i, j);
                }
            }
        }
    }
    public static void main(String[] args) {
        MinNumberSolution solution = new MinNumberSolution();
        System.out.println(("1" + "2").compareTo("2" + "1"));
        int[] nums = {3,30,34,5,9};
        String s = solution.minNumber(nums);
        System.out.println(s);
    }

}
