package org.example.xyl.top100.number;

import org.example.xyl.top100.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiangyanlin
 * @date 2022/8/10
 */
public class NumberSolution {

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
            if (map.containsKey(nums[i])) {
                map1.put(nums[i], i);
                continue;
            }
            map.put(nums[i], i);
        }
        int l = 0, r = length - 1;
        Arrays.sort(nums);
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                if (nums[l] == nums[r]) {
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


    //15

    /**
     * 三数之和
     *
     * @param nums 3 <= nums.length <= 3000
     *             -105 <= nums[i] <= 105
     * @return 所有组合
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        int left, right;
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去掉重复情况
            }
            int first = nums[i];
            int target = -first;
            left = i + 1;
            right = length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(first);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    // 首先无论如何先要进行加减操作
                    left++; right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                }
            }

        }
        return result;
    }


    /**
     * 官方答案
     */
    public List<List<Integer>> threeSum1(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return ans;
        }

        Arrays.sort(nums); // O(nlogn)

        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) {
                break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去掉重复情况
            }
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++; right--; // 首先无论如何先要进行加减操作
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        NumberSolution solution = new NumberSolution();
//        int[] arr = solution.twoSum(, 9);
//        System.out.println(Arrays.toString(arr));

//        ListNode l1 = new ListNode(9);
//        l1.next = new ListNode(9);
//        l1.next.next = new ListNode(9);
//        l1.next.next.next = new ListNode(9);
//        l1.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next.next = new ListNode(9);
//
//        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(9);
//        l2.next.next = new ListNode(9);
//        l2.next.next.next = new ListNode(9);
//        ListNode node = solution.addTwoNumbers(l1, l2);
//        while (node != null) {
//            System.out.println(node.val);
//            node = node.next;
//        }
//        int[] nums = new int[]{-1,0,1,2,-1,-4};
        int[] nums = new int[]{0, 0, 0, 0};
        List<List<Integer>> lists = solution.threeSum(nums);
        List<List<Integer>> lists1 = solution.threeSum1(nums);
        System.out.println(lists.toString());
        System.out.println(lists1.toString());
    }
}
