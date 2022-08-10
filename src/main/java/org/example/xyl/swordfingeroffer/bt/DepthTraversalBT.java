package org.example.xyl.swordfingeroffer.bt;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 重点：每个节点会到达三次
 * @author xiangyanlin
 * @date 2022/6/10
 */
public class DepthTraversalBT {


    public static void main(String[] args) {
//        DepthTraversalBT test = new DepthTraversalBT();
//        TreeNode head = new TreeNode(1);
//        head.left = new TreeNode(2);
//        head.right = new TreeNode(3);
//        head.left.left = new TreeNode(4);
//        head.left.right = new TreeNode(5);
//        head.right.left = new TreeNode(6);
//        head.right.right = new TreeNode(7);
//
//        List<List<Integer>> lists = test.pathSum(head, 7);
//        System.out.println("========");

        int[] postorder = {1,3,2,6,5};
        DepthTraversalBT test = new DepthTraversalBT();
        test.verifyPostorder(postorder);
    }

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     */
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Deque<Integer> path = new LinkedList<Integer>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target);
        return ret;
    }

    /**
     * 先序遍历
     */

    public void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        target -= root.val;
        //叶子节点
        if (root.left == null && root.right == null ) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, target);
        dfs(root.right, target);
        path.pollLast();
    }


    /**
     * Offer 33. 二叉搜索树的后序遍历序列
     * 左右子树为子问题  找到正确的根是当前返回
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }
    boolean recur(int[] postorder, int i, int j) {
        if(i >= j) {
            return true;
        }
        int p = i;
        while(postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while(postorder[p] > postorder[j]) {
            p++;
        }
        boolean cur = p == j;
        boolean left = recur(postorder, i, m - 1);
        boolean right = recur(postorder, m, j - 1);
        return cur && left && right;
    }


}
