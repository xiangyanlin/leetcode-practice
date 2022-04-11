package org.example.xyl.swordfingeroffer;

/**
 * 双指针
 *
 * @author xiangyanlin
 * @date 2022/4/8
 */
public class DoublePointerSolution {
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        deleteNextNode(head, head.next, val);
        return head;
    }

    public void deleteNextNode(ListNode head, ListNode next, int val) {
        if (next == null) {
            return;
        }
        if (next.val == val) {
            head.next = next.next;
            return;
        }
        deleteNextNode(next, next.next, val);

    }

    public ListNode deleteNode1(ListNode head, int val) {
        if(head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while(cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if(cur != null) pre.next = cur.next;
        return head;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        while (head !=null) {
            ListNode last = head;
            int p = k;
            while (p != 0){
                last = last.next;
                p--;
            }
            if(last == null) {
                return head;
            }
            head = head.next;
        }
        return head;
    }

    /**
     * 我们首先求出链表的长度 nn，然后顺序遍历到链表的第 n - kn−k 个节点返回即可
     */
    public ListNode getKthFromEnd1(ListNode head, int k) {
        int n = 0;
        ListNode node = null;

        for (node = head; node != null; node = node.next) {
            n++;
        }
        for (node = head; n > k; n--) {
            node = node.next;
        }

        return node;
    }


    public static void main(String[] args) {
        DoublePointerSolution test = new DoublePointerSolution();
        ListNode head = new ListNode(1);
        head.next  = new ListNode(2);
        head.next.next  = new ListNode(3);
        head.next.next.next  = new ListNode(4);
        head.next .next.next.next = new ListNode(5);
        test.getKthFromEnd(head, 2);
    }

}


