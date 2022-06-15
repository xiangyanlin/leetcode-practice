package org.example.xyl.swordfingeroffer.sort;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 * @author xiangyanlin
 * @date 2022/6/13
 */
public class MinNumberSolution {

    public String minNumber(int[] nums) {
        StringBuilder res = new StringBuilder();
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        sort(strs);
        for(String s : strs) {
            res.append(s);
        }
        return res.toString();

    }

    public void sort(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            for (int j = i+1; j < strs.length; j++) {
                if((strs[i] + strs[j]).compareTo(strs[j] + strs[i]) >= 0) {
                    swap(strs, i, j);
                }
            }
        }
    }

    public void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        MinNumberSolution solution = new MinNumberSolution();
        System.out.println(("1" + "2").compareTo("2" + "1"));
        int[] nums =  {1, 2};
        String s = solution.minNumber(nums);
        System.out.println(s);
    }

}
