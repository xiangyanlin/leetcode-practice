package org.example.xyl.swordfingeroffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import javax.swing.tree.TreeNode;

/**
 * 搜索与回溯算法（简单）
 *
 * @author xiangyanlin
 * @date 2022/2/8
 */
public class SearchBacktrackingSolution {

    //--------------------------------------广度搜索优先-----------------------------------------

    private final List<List<Integer>> result = new ArrayList<>();

    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (Objects.isNull(root)) {
            return result;
        }
        List<Integer> rootVal = new ArrayList<>();
        rootVal.add(root.val);
        result.add(rootVal);

        List<TreeNode> fathers = new ArrayList<>();
        fathers.add(root);
        addChildNode(fathers);
        return result;
    }


    public void addChildNode(List<TreeNode> fathers) {
        if (null == fathers || fathers.isEmpty()) {
            return;
        }
        List<TreeNode> children = new ArrayList<>();
        List<Integer> childVal = new ArrayList<>();
        for (TreeNode father : fathers) {
            if (null == father.left && null == father.right) {
                continue;
            }
            if (null != father.left) {
                childVal.add(father.left.val);
                children.add(father.left);
            }
            if (null != father.right) {
                childVal.add(father.right.val);
                children.add(father.right);
            }
        }
        if (!childVal.isEmpty()) {
            result.add(childVal);
        }
        addChildNode(children);
    }


    /**
     * 参考--效率和我自己的差不多
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }


    public int[] levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();

    }

    public int[] levelOrder3(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }


//--------------------------------------------------------------------

    /**
     * 请实现一个函数按照之字形顺序打印二叉树，
     * 即第一行按照从左到右的顺序打印，
     * 第二层按照从右到左的顺序打印，
     * 第三行再按照从左到右的顺序打印，其他行以此类推。
     */
    public List<List<Integer>> levelOrder4(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) {
            deque.add(root);
        }
        while(!deque.isEmpty()) {
            // 打印奇数层
            List<Integer> tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) {
                // 从左向右打印
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                // 先左后右加入下层节点
                if(node.left != null) {
                    deque.addLast(node.left);
                }
                if(node.right != null) {
                    deque.addLast(node.right);
                }
            }
            res.add(tmp);
            if(deque.isEmpty()) {
                break; // 若为空则提前跳出
            }
            // 打印偶数层
            tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) {
                // 从右向左打印
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // 先右后左加入下层节点
                if(node.right != null) {
                    deque.addFirst(node.right);
                }
                if(node.left != null) {
                    deque.addFirst(node.left);
                }
            }
            res.add(tmp);
        }
        return res;
    }
//--------------------------------------深度搜索优先-----------------------------------------------------

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
