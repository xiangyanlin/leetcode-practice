package org.example.xyl.top100.string;

import java.util.HashSet;
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


    //----------------- 最长回文子串-----------------------------

    char[] str;
    int max = 1;
    /**
     * 最长回文子串
     * 官方答案：动态规划
     * 1 <= s.length <= 1000
     * @param s 仅由数字和英文字母组成  1 <= s.length <= 1000
     * @return 最长回文子串
     */


    /**
     * 官方答案：动态规划
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    //L
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }



    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
//        String s = "bbbbb";
        String s = "abcabcbb";
//        s = "aabaab!bb";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

}
