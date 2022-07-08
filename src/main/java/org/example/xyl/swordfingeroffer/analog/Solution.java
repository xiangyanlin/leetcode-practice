package org.example.xyl.swordfingeroffer.analog;

import java.util.Stack;

/**
 * @author xiangyanlin
 * @date 2022/7/8
 */
public class Solution {

    // ---------------剑指 Offer 29. 顺时针打印矩阵---------------


    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        //上下左右边界
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        //返回
        int[] res = new int[(r + 1) * (b + 1)];
        //返回下标
        int index = 0;
        while (true) {
            //右移
            for (int i = l; i <= r; i++) {
                res[index++] = matrix[t][i];
            }
            //上边界减一
            if(++t > b) {
                break;
            }

            //下移
            for (int i = t; i <= b; i++) {
                res[index++] = matrix[i][r];
            }
            //右边界减一
            if(--r < l) {
                break;
            }

            //左移
            for (int i = r; i >= l; i--) {
                res[index++] = matrix[b][i];
            }
            //下边界减一
            if(--b < t) {
                break;
            }
            
            //上移
            for (int i = b; i >= t; i--) {
                res[index++] = matrix[i][l];
            }
            //左边界减一
            if(++l > r){
                break;
            }
        }
        return res;

    }

    //---------- 剑指 Offer 31. 栈的压入、弹出序列---------------


    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed) {
            stack.push(num); // num 入栈
            while(!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        boolean b = solution.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
        boolean b = solution.validateStackSequences(new int[]{2 ,1 ,0}, new int[]{1 ,2, 0});
//        boolean b = solution.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2});
        System.out.println(b);
    }
}
