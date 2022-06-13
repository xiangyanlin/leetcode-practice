package org.example.xyl.swordfingeroffer.bt;

/**
 * @author xiangyanlin
 * @date 2022/6/13
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
