package org.example.xyl.swordfingeroffer;

import java.util.List;
import java.util.Stack;

/**
 * 链表
 */
public class Solution {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        reversePrint(head);
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * 示例 1：
     * <p>
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     */
    public static int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null ) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }


    /**
     * 深拷贝
     */
    public static Node copyRandomList(Node head) {
        Node result;
        //next random
        Node temp = head;
        result = temp;
        result.random = temp.random;
        result.next = temp.next;
        //递归下一层
        while (head.next != null ) {
            copyRandomList(head.next);
        }
        return result;
    }
}



class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}