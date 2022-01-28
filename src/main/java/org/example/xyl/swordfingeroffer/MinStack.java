package org.example.xyl.swordfingeroffer;

import java.util.Stack;

/**
 * 定义栈的数据结构，
 * 请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public class MinStack {

    Stack<Integer> A, B;

    /** initialize your data structure here. */
    public MinStack() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void push(int x) {
        A.push(x);
        if(B.empty()) {
            B.push(x);
        } else if(x <= B.peek()) {
            B.push(x);
        }
    }

    public void pop() {
        Integer pop = A.pop();
        if(pop.equals(B.peek()) ) {
            B.pop();
        }
    }

    public int top() {
        return A.peek();
    }

    public int min() {
        return B.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("返回 -3:" + minStack.min());
        minStack.pop();//
        System.out.println("--> 返回 0:" + minStack.top());
        System.out.println(" --> 返回 -2.:" + minStack.min());

    }
}
