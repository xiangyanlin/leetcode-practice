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
        if (head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) pre.next = cur.next;
        return head;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        while (head != null) {
            ListNode last = head;
            int p = k;
            while (p != 0) {
                last = last.next;
                p--;
            }
            if (last == null) {
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

    /**
     * 合并两个排序的列表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }


    }


    /**
     * 两个列表第一个公共节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }




    public static void main(String[] args) {
        DoublePointerSolution test = new DoublePointerSolution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        //test.getKthFromEnd(head, 2);

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode listNode = test.mergeTwoLists(l1, l2);
        System.out.println("1");
    }

}


