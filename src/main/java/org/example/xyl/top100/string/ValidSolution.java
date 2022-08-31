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
     * 有效的括号
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
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    /**
     * 括号生成
     * 官方题解 动态规划
     * @param n 1 <= n <= 8
     * @return 排列组合
     */
    public List<String> generateParenthesis1(int n) {
        return generate(n);
    }

    public static void main(String[] args) {
        ValidSolution solution = new ValidSolution();
//        System.out.println(solution.isValid("()[]{}"));
        List<String> list = solution.generateParenthesis(3);
        System.out.println(list.toString());
    }
}
