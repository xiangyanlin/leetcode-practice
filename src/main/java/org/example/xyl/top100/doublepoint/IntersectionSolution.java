package org.example.xyl.top100.doublepoint;

import org.example.xyl.swordfingeroffer.FindFirstIntersectNode;
import org.example.xyl.top100.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiangyanlin
 * @date 2022/12/9
 */
public class IntersectionSolution {


    /**
     * 相交链表
     *
     * @param headA listA 中节点数目为 m
     * @param headB listB 中节点数目为 n
     *              1 <= m, n <= 3 * 104
     *              1 <= Node.val <= 105
     *              0 <= skipA <= m
     *              0 <= skipB <= n
     *              如果 listA 和 listB 没有交点，intersectVal 为 0
     *              如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
     * @return intersectVal
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;

        int n = 0;
        while (curA.next != null) {
            n++;
            curA = curA.next;
        }
        while (curB.next != null) {
            n--;
            curB = curB.next;
        }
        //如果最后以后节点都不相等 则必不可能相交
        if (curA != curB) {
            return null;
        }

        // n  :  链表1长度减去链表2长度的值
        // 谁长，谁的头变成curA
        curA = n > 0 ? headA : headB;
        // 谁短，谁的头变成curB
        curB = curA == headA ? headB : headA;
        n = Math.abs(n);

        while (n != 0) {
            n--;
            curA = curA.next;
        }
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }


    /**
     * 解法2 使用HashSet
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }


    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
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
        IntersectionSolution solution = new IntersectionSolution();
        // 1->2->3->4->5->6->7->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->null
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(solution.getIntersectionNode(head1, head2).val);
    }
}
