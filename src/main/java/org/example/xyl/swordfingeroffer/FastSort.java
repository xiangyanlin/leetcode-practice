package org.example.xyl.swordfingeroffer;

/**
 * @author xiangyanlin
 * @date 2022/4/12
 */
public class FastSort {

    /**
     * 快速排序
     */
    public void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //取随机一个位置和最后一个交换作为比较的基数
        int index = (int) Math.random() * (R - L + 1);
        swap(arr, index, R);
        //获取左右边界
        int[] border = partition(arr, L, R);
        quickSort(arr, L, border[0] - 1);
        quickSort(arr, border[1] + 1, R);

    }

    public int[] partition(int[] arr, int l, int r) {
        //左边界
        int less = l - 1;
        //右边界
        int more = r;
        //移动指针：l
        while (l < more) {
            int num = arr[r];
            if (arr[l] == num) {
                //等于基数边界不变
                l++;
            } else if (arr[l] < num) {
                //当前值arr[l] 小于num 左边界右移动 arr[l]交换和边界值和左边界值交换
                swap(arr, ++less, l++);
            } else {
                //当前值arr[l] 大于num arr[l]和右边界值的前一个做交换
                swap(arr, --more, l);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, r};
    }


    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        FastSort test = new FastSort();
        int[] arr = {5, 3, 3, 2, 1, 4, 6, 3, 9};
        test.quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
