package org.example.xyl.swordfingeroffer.bt;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉树序列化
 * @author xiangyanlin
 * @date 2022/7/13
 */
public class SerializeAndReconstructTreeSolution {

    //-------------------- 剑指offer 37 序列化二叉树 ---------------------------

    /**
     * Encodes a tree to a single string.
     * 使用宽度优先遍历
     */
    public String serialize(TreeNode root) {
        Queue<String> res = new LinkedList<>();
        if(root == null) {
            res.add(null);
            return String.join(",", res);
        }
        Queue<TreeNode> level = new LinkedList<>();
        level.add(root);
        res.add(String.valueOf(root.val));
        while (!level.isEmpty()) {
            TreeNode head = level.poll();
            if(Objects.nonNull(head.left)) {
                res.add(String.valueOf(head.left.val));
                level.add(head.left);
            } else {
                res.add(null);
            }
            if(Objects.nonNull(head.right)) {
                res.add(String.valueOf(head.right.val));
                level.add(head.right);
            } else {
                res.add(null);
            }
        }
        return String.join(",", res);
    }

    /**
     *  Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }
        String[] valArray = data.split(",");
        Queue<TreeNode> level = new LinkedList<>();
        int index = 0;
        TreeNode head = buildTreeNode(valArray[index]);
        if (Objects.nonNull(head)) {
            level.add(head);
        }
        TreeNode node = null;
        while (!level.isEmpty()) {
            node = level.poll();
            index++;
            node.left = buildTreeNode(valArray[index]);
            index++;
            node.right = buildTreeNode(valArray[index]);
            if (Objects.nonNull(node.left)) {
                level.add(node.left);
            }
            if (Objects.nonNull(node.right)) {
                level.add(node.right);
            }
        }
        return head;
    }
    public TreeNode buildTreeNode(String val) {
        if(Objects.equals(val, "null")) {
            return null;
        }
        return new TreeNode(Integer.parseInt(val));
    }


    public static void main(String[] args) {
        SerializeAndReconstructTreeSolution solution = new SerializeAndReconstructTreeSolution();
        TreeNode head = new TreeNode(1);
        head.left =  new TreeNode(2);
        head.right =  new TreeNode(3);
        head.left.left =  null;
        head.left.right =  null;
        head.right.left =  new TreeNode(4);
        head.right.right =  new TreeNode(5);
        String serialize = solution.serialize(head);
        TreeNode deserialize = solution.deserialize(serialize);
        System.out.println("111");
    }


}
