package org.example.xyl.top100.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
     *
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


    //-----------------------电话号码的字母组合----------------------------


    /**
     * 电话号码的字母组合
     *
     * @param digits 0 <= digits.length <= 4
     *               digits[i] 是范围 ['2', '9'] 的一个数字。
     * @return 电话号码的字母组合集合
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        char[] numbers = digits.toCharArray();
        List<String> combinations = new ArrayList<>();
        //按键组合
        String[][] digitsStr = {
                {"a", "b", "c"},
                {"d", "e", "f"},
                {"g", "h", "i"},
                {"j", "k", "l"},
                {"m", "n", "o"},
                {"p", "q", "r", "s"},
                {"t", "u", "v"},
                {"w", "x", "y", "z"},
        };
        //0 -- 7
        // 2 -9
        for (char number : numbers) {
            int digitsIndex = Integer.parseInt(String.valueOf(number)) - 2;
            String[] curDigitsStr = digitsStr[digitsIndex];
            if (combinations.isEmpty()) {
                combinations = new ArrayList<>(Arrays.asList(curDigitsStr));
                continue;
            }
            List<String> temp = new ArrayList<>();
            for (String combination : combinations) {
                for (String value : curDigitsStr) {
                    String s = combination + value;
                    temp.add(s);
                }
            }
            combinations.clear();
            combinations.addAll(temp);
        }
        return combinations;
    }


    /**
     * 官方答案
     */
    public List<String> letterCombinations1(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }


    /**
     * 1839. 所有元音按顺序排布的最长子字符串
     *
     * @param word 1 <= word.length <= 5 * 105
     *             word 只包含字符 'a'，'e'，'i'，'o' 和 'u'
     * @return 最长子字符串V长度
     */
    public int longestBeautifulSubstring(String word) {
        //1.首先如果数组长度小于5的话，不可能满足美丽的定义，将这种情况提前排除
        if (word.length() < 5) {
            return 0;
        }
        char[] chars = word.toCharArray();
        int res = 0;
        int rlen = 1;
        int vowel = 1;
        for (int i = 1; i < chars.length; i++) {
            //- 如果当前字符>= 上一个小（顺序意义），那么当前子串长度+1
            if (chars[i] >= chars[i - 1]) {
                rlen++;
            }
            //- 如果当前字符比上一个大，那么子串中元音字母种类+1
            if (chars[i] > chars[i - 1]) {
                vowel++;
            }
            //- 如果当前字符比上一个小，那么肯定当前字串不美丽，以当前字符为首继续进行遍历
            if (chars[i] < chars[i - 1]) {
                rlen = 1;
                vowel = 1;
            }
            if (vowel == 5) {
                res = Math.max(rlen, res);
            }

        }
        return res;
    }


    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
//        String s = "bbbbb";
//        String s = "abcabcbb";
//        s = "aabaab!bb";
//        System.out.println(solution.lengthOfLongestSubstring(s));
        String digits1 = "23";
        String digits2 = "";
        String digits3 = "2";
        System.out.println(solution.letterCombinations(digits1).toString());
        System.out.println(solution.letterCombinations(digits2).toString());
        System.out.println(solution.letterCombinations(digits3).toString());
        System.out.println(solution.letterCombinations1(digits1).toString());
        System.out.println(solution.letterCombinations1(digits2).toString());
        System.out.println(solution.letterCombinations1(digits3).toString());
    }

}
