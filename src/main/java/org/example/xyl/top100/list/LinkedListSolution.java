package org.example.xyl.top100.list;

import org.example.xyl.top100.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

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



    ListNode[] lists;

    /**
     * 合并k个升序列表
     * 顺序合并 自己的解
     * @param lists k == lists.length
     *              0 <= k <= 10^4  链表个数
     *              0 <= lists[i].length <= 500  单个链表长度
     *              -10^4 <= lists[i][j] <= 10^4 node取值范围
     *              lists[i] 按 升序 排列
     *              lists[i].length 的总和不超过 10^4
     * @return 链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }
        int k = lists.length;
        this.lists = lists;

        return mergeKListsProcess(k);
    }


    public ListNode mergeKListsProcess(int n) {

        //base case
        if(n == 1) {
            return lists[0];
        }
        //合并两个 k 与k-1 合并
        ListNode preList = mergeKListsProcess(n - 1);
        ListNode curList = lists[n - 1];

        return mergeTwoLists(preList, curList);

    }


    /**
     * 合并k个升序列表
     * 分治合并  官方答案
     * @param lists k == lists.length
     *              0 <= k <= 10^4  链表个数
     *              0 <= lists[i].length <= 500  单个链表长度
     *              -10^4 <= lists[i][j] <= 10^4 node取值范围
     *              lists[i] 按 升序 排列
     *              lists[i].length 的总和不超过 10^4
     * @return 链表
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }


    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists2(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }




    public static void main(String[] args) {

        LinkedListSolution solution = new LinkedListSolution();

        ListNode[] lists = new ListNode[3];
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        lists[0] = l1;

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        lists[1] = l2;


        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        lists[2] = l3;


        ListNode listNode = solution.mergeKLists(lists);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}
