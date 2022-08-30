package org.example.xyl.top100.string;

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

    public static void main(String[] args) {
        ValidSolution solution = new ValidSolution();
        System.out.println(solution.isValid("()[]{}"));
    }
}
