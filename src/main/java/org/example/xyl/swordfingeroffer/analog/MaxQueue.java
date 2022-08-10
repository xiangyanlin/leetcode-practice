package org.example.xyl.swordfingeroffer.analog;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * @author xiangyanlin
 * @date 2022/7/11
 */
class MaxQueue {

    Queue<Integer> q;
    Deque<Integer> d;

    public MaxQueue() {
        q = new LinkedList<Integer>();
        d = new LinkedList<Integer>();
    }

    public int max_value() {
        if (d.isEmpty()) {
            return -1;
        }
        return d.peekFirst();
    }

    public void push_back(int value) {
        while (!d.isEmpty() && d.peekLast() < value) {
            d.pollLast();
        }
        d.offerLast(value);
        q.offer(value);
    }

    public int pop_front() {
        if (q.isEmpty()) {
            return -1;
        }
        int ans = q.poll();
        if (ans == d.peekFirst()) {
            d.pollFirst();
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();
        queue.push_back(1);
        queue.push_back(3);
        queue.push_back(4);
        queue.push_back(2);
        Queue<Integer> q1 = queue.q;
        Deque<Integer> d1 = queue.d;
        System.out.println(queue.max_value());
        queue.pop_front();
        queue.pop_front();
        queue.pop_front();
        Queue<Integer> q2 = queue.q;
        Deque<Integer> d2 = queue.d;
        System.out.println(queue.max_value());
    }
}
