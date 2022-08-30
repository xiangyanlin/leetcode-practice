package org.example.xyl.top100.list;

import org.example.xyl.top100.ListNode;

/**
 * @author xiangyanlin
 * @date 2022/8/30
 */
public class LinkedListSolution {

    /**
     * 合并两个有序列表
     * @param list1 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     *
     * @param list2 l1 和 l2 均按 非递减顺序 排列
     * @return 合并后的链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        else if (list2 == null) {
            return list1;
        }
        else if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
