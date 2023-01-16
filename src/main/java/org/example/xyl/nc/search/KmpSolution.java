package org.example.xyl.nc.search;

import java.util.Arrays;

/**
 * NC149 kmp算法
 * @author xiangyanlin
 * @date 2023/1/13
 */
public class KmpSolution {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 计算模板串S在文本串T中出现了多少次
     *
     * @param S string字符串 模板串
     * @param T string字符串 文本串
     * @return int整型
     */
    public int kmp(String S, String T) {
        // write code here
        int m = S.length(), n = T.length();
        if (m > n || n == 0) {
            return 0;
        }
        //初始化计数，获取next数组
        int cnt = 0;
        int[] next = getNext(S);

        //遍历主串和模式串
        for (int i = 0, j = 0; i < n; i++) {
            //只要不相等，回退到next数组记录的下一位
            while (j > 0 && T.charAt(i) != S.charAt(j)) {
                j = next[j - 1];
            }
            if (T.charAt(i) == S.charAt(j)) {
                j++;
            }
            //如果j为m，说明完全匹配一次
            if (j == m) {
                //计数加一，索引回退到next数组记录的下一位
                cnt++;
                j = next[j - 1];
            }
        }
        return cnt;
    }


    /**
     * 确定next数组
     */
    public int[] getNext(String S) {
        int m = S.length();
        int[] next = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            //只要不相等，回退到next数组记录的下一位
            while (j > 0 & S.charAt(i) != S.charAt(j)) {
                j = next[j - 1];
            }
            //前缀索引后移
            if (S.charAt(i) == S.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        KmpSolution solution = new KmpSolution();
        int[] next = solution.getNext("abcacab");
        System.out.println(Arrays.toString(next));
    }
}
