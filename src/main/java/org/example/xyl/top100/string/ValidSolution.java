package org.example.xyl.top100.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xiangyanlin
 * @date 2022/8/30
 */
public class ValidSolution {

    /**
     * 20 有效的括号
     *
     * @param s 1 <= s.length <= 104
     *          s 仅由括号 '()[]{}' 组成
     * @return 是否有效
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char ch : chars) {
            //如果是左括号
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                //右括号
                if (stack.isEmpty() || !validBrackets(stack.pop(), ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 1614. 括号的最大嵌套深度
     *
     * @param s 1 <= s.length <= 100
     *          s 由数字 0-9 和字符 '+'、'-'、'*'、'/'、'('、')' 组成
     *          题目数据保证括号表达式 s 是 有效的括号表达式
     * @return 最大深度
     */
    public int maxDepth(String s) {
        char[] chars = s.toCharArray();
        int max = 0, depth = 0;
        for (char ch : chars) {
            //如果是左括号
            if (ch == '(') {
                depth++;
                max = Math.max(depth, max);
            } else if (ch == ')') {
                //右括号
                depth--;
            }
        }
        return max;
    }

    public boolean validBrackets(char left, char right) {
        if (left == '(') {
            return right == ')';
        }
        if (left == '[') {
            return right == ']';
        }
        if (left == '{') {
            return right == '}';
        }
        return false;
    }


    /**
     * 括号生成
     *
     * @param n 1 <= n <= 8
     * @return 排列组合
     */
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return res;
        }
        process("", 0, 0, n);
        return res;
    }

    private void process(String str, int open, int close, int n) {
        if (open == n && close == n) {
            res.add(str);
            return;
        }
        if (open == close) {
            //剩余左右括号数相等，下一个只能用左括号
            process(str + "(", open + 1, close, n);
        } else if (open > close) {
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if (open < n) {
                process(str + "(", open + 1, close, n);
            }
            process(str + ")", open, close + 1, n);
        }
    }


    ArrayList[] cache = new ArrayList[100];

    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : generate(c)) {
                    for (String right : generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }


    /**
     * 最长有效括号
     *
     * @param s 0 <= s.length <= 3 * 104
     *          s[i] 为 '(' 或 ')'
     * @return 子括号数量
     */
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0
                        && s.charAt(i - dp[i - 1] - 1) == '(') {
                    //                               dp[i - 1]是上个代表的有效子串
                    //s.charAt(i - dp[i - 1] - 1)    第i - dp[i - 1]个 必须是 '('

                    // sub为前一个"）"  所在的有效 子集合
                    int sub = (i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0;
                    dp[i] = dp[i - 1] + sub + 2;
                }
                //不满足条件则代表  以当前符号结尾无法找到有效的  所以取默认值0

                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }


    /**
     * 括号生成
     * 官方题解 动态规划
     *
     * @param n 1 <= n <= 8
     * @return 排列组合
     */
    public List<String> generateParenthesis1(int n) {
        return generate(n);
    }

    public static void main(String[] args) {
        ValidSolution solution = new ValidSolution();
//        System.out.println(solution.isValid("()[]{}"));
//        List<String> list = solution.generateParenthesis(3);
        int i = solution.longestValidParentheses("()()()()))");
        System.out.println(i);
    }
}
