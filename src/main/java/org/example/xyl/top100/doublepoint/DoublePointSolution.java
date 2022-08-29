package org.example.xyl.top100.doublepoint;

import org.example.xyl.top100.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xiangyanlin
 * @date 2022/8/29
 */
public class DoublePointSolution {


    /**
     * 删除链表的倒数第N个节点
     * 思路： 计算链表长度
     * @param head 链表中结点的数目为 sz
     *             1 <= sz <= 30
     *             0 <= Node.val <= 100
     * @param n    1 <= n <= sz
     * @return 链表
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        //往后还有n个

        //当前节点
        ListNode node = head;
        //前一个节点
        ListNode pre = null;
        int i = iterateNode(node);
        while (node != null) {

            if (i == n) {
                if (pre != null) {
                    pre.next = node.next;
                } else {
                    return head.next;
                }
                break;
            }
            i --;
            pre = node;
            node = node.next;
        }
        return head;
    }

    public int iterateNode(ListNode node) {
        int r = 0;
        while (node != null) {
            r++;
            node = node.next;
        }
        return r;
    }


    /**
     * 删除链表的倒数第N个节点
     * 思路： 栈
     * @param head 链表中结点的数目为 sz
     *             1 <= sz <= 30
     *             0 <= Node.val <= 100
     * @param n    1 <= n <= sz
     * @return 链表
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    /**
     * 删除链表的倒数第N个节点
     * 思路： 栈
     * @param head 链表中结点的数目为 sz
     *             1 <= sz <= 30
     *             0 <= Node.val <= 100
     * @param n    1 <= n <= sz
     * @return 链表
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }

        // 从 n-开始
        while (first != null) {
            first = first.next;
            //移动 sz - n 个
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;

    }


    public static void main(String[] args) {
        DoublePointSolution solution = new DoublePointSolution();
        ListNode head = new ListNode(1);
         head.next = new ListNode(2);
        ListNode listNode = solution.removeNthFromEnd(head, 2);
        System.out.println(listNode.toString());
    }
}
