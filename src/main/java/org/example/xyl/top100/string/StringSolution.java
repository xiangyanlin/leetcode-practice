package org.example.xyl.top100.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xiangyanlin
 * @date 2022/8/16
 */
public class StringSolution {


    //---------------  无重复字符最长子串----------------------------------


    /**
     * 滑动窗口 -- 官方答案
     * 无重复字符最长子串
     * @param s 由英文字母、数字、符号和空格组成 0 <= s.length <= 5 * 104
     * @return length
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
//        String s = "bbbbb";
        String s = "abcabcbb";
//        s = "aabaab!bb";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

}
