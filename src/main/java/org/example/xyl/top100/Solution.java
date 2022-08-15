package org.example.xyl.top100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author xiangyanlin
 * @date 2022/8/10
 */
public class Solution {

    /**
     * 两数之和
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     */
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        //如何保存相同的值
        Map<Integer, Integer> map = new HashMap<>(length);
        Map<Integer, Integer> map1 = new HashMap<>(length);

        //建立索引
        for (int i = 0; i < length; i++) {
            if(map.containsKey(nums[i])) {
                map1.put(nums[i], i);
                continue;
            }
            map.put(nums[i], i);
        }
        int l = 0, r = length - 1;
        Arrays.sort(nums);
        while (l < r) {
            if(nums[l] + nums[r] == target) {
                if(nums[l] == nums[r]) {
                    return new int[]{map.get(nums[l]), map1.get(nums[r])};
                }
                return new int[]{map.get(nums[l]), map.get(nums[r])};
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

    /**
     * 官方解法
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


    int next = 0;

    /**
     * 两数相加
     *
     * @param l1 非负的整数 逆序
     * @param l2 非负的整数 逆序
     * @return 两数之和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //base case
        if (l1 == null && l2 == null) {
            if (next != 0) {
                return new ListNode(next);
            }
            return null;
        }

        int bit = next;
        next = 0;
        if (l1 != null) {
            bit += l1.val;
        }
        if (l2 != null) {
            bit += l2.val;
        }
        //求个位数
        int val = 0;
        if (bit < 10) {
            val = bit;
        } else {
            val = bit % 10;
            next = bit / 10;
        }
        ListNode node = new ListNode(val);
        ListNode next1 = l1 == null ? null : l1.next;
        ListNode next2 = l2 == null ? null : l2.next;
        node.next = addTwoNumbers(next1, next2);
        return node;
    }

    /**
     * 官方解答
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] arr = solution.twoSum(new int[]{2, 7, 11, 5}, 9);
//        System.out.println(Arrays.toString(arr));

        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        ListNode node = solution.addTwoNumbers(l1, l2);
        while (node!= null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}
