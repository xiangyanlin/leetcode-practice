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
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
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

        int[] nums = {2, 7, 11, 15};
        int[] ints = test.twoSum(nums, 9);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }


    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int p1 = 0;
        int p2 = nums.length - 1;
        while (p1 < p2) {
            if (nums[p1] % 2 == 0) {
                swap(nums, p1, p2);
                p2--;
            } else {
                p1++;
            }
        }
        return nums;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     *
     * @param nums   递增排序的数组
     * @param target 数字
     * @return 输出任意一对即可
     */
    public int[] twoSum(int[] nums, int target) {
        int p1 = 0;
        int p2 = nums.length - 1;
        while (p1 < p2) {
            if (nums[p1] + nums[p2] < target) {
                p1++;
            } else if (nums[p1] + nums[p2] > target) {
                p2++;
            } else {
                return new int[]{nums[p1], nums[p2]};
            }
        }
        return null;
    }

    /**
     * 单词翻转
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        if ("".equals(s) || s == null) {
            return "";
        }
        String[] r = s.split("\\s+");
        StringBuilder sr = new StringBuilder();
        for (int i = r.length - 1; i >= 0; i--) {
            sr.append(r[i]);
            if (i != 0) {
                sr.append(" ");
            }
        }
        return sr.toString();
    }

}


