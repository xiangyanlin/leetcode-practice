package org.example.xyl.swordfingeroffer.sort;

/**
 * @author xiangyanlin
 * @date 2022/7/14
 */
public class SortSolution {

    //-----------剑指offer17打印从1到最大的n为数 -----------

    public int[] printNumbers(int n) {
        int length = (int) Math.pow(10, n) - 1;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    //-----------剑指offer51 数组中的逆序对 -----------

    int[] nums, tmp;

    public int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        this.nums = nums;
        tmp = new int[nums.length];
        //每个数和它后面的数作比较O(n^2) 0 <= 数组长度 <= 50000 不可取
        //归并 1中点
        return partition(0, nums.length - 1);
    }

    public int partition(int l, int r) {
        //base case
        if (l >= r) {
            return 0;
        }
        //求中点
        int m = (l + r) / 2;
        //调用自身
        //两部分的和  以及 左边能和右边组成的新逆序对
        int res = partition(l, m) + partition(m + 1, r);
        //合并
        int i = l, j = m + 1;
        if (r + 1 - l >= 0) {
            System.arraycopy(nums, l, tmp, l, r + 1 - l);
        }
        //排序与统计  是的nums 从 l 到 r有序
        for (int k = l; k <= r; k++) {
            if (i == m + 1) {
                //左边已经排完。 右边直接全部拿过来
                nums[k] = tmp[j++];
            } else if (j == r + 1 || tmp[i] <= tmp[j]) {
                //右边已经全部排完 或者 左边的值小于右边  吧左边的值放过去
                nums[k] = tmp[i++];
            } else {
                //左右都没有排完  并且 左边大于右边
                nums[k] = tmp[j++];
                // 统计逆序对 i 以及 i到 m 都大于 右边  都组成一个逆序对
                res += m - i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SortSolution solution = new SortSolution();
        int i = solution.reversePairs(new int[]{7, 5, 6, 4});
        System.out.println(i);
    }
}
