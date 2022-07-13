package org.example.xyl.swordfingeroffer.sort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指offer41 数据流中的中位数
 * @author xiangyanlin
 * @date 2022/7/13
 */
public class MedianFinder {


    Queue<Integer> queueA, queueB;


    /** initialize your data structure here. */
    public MedianFinder() {
        //小顶堆 保存较大的一般数据
        queueA = new PriorityQueue<>();
        //大顶堆 保存叫下的半数据
        queueB = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        if(queueA.size() != queueB.size()) {
            queueA.add(num);
            queueB.add(queueA.poll());
        } else {
            queueB.add(num);
            queueA.add(queueB.poll());
        }

    }

    public double findMedian() {
        return queueA.size() != queueB.size() ? queueA.peek() : (queueA.peek() + queueB.peek()) / 2.0;
    }
}
