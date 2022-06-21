package org.example.xyl.swordfingeroffer.bt;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyanlin
 * @date 2022/6/17
 */
public class SerializeTreeSolution {



    private Map<Integer, Integer> indexMap;

    /**
     * 构建二叉树
     * @param preorder 前序遍历
     * @param preorderLeft
     * @param preorderRight
     * @param inorderLeft
     * @param inorderRight
     */
    public TreeNode myBuildTree(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorderRootIndex = preorderLeft;
        int rootValue = preorder[preorderRootIndex];
        // 在中序遍历中定位根节点
        int inorderRoot = indexMap.get(rootValue);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(rootValue);

        // 得到左子树中的节点数目
        int sizeLeftSubtree = inorderRoot - inorderLeft;

        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素
        // 就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, preorderLeft + 1, preorderLeft + sizeLeftSubtree, inorderLeft, inorderRoot - 1);

        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素
        // 就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, preorderLeft + sizeLeftSubtree + 1, preorderRight, inorderRoot + 1, inorderRight);
        return root;
    }

    /**
     * 剑指 Offer 07. 重建二叉树
     * @param preorder 前序遍历
     * @param inorder 中序遍历
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        //key:节点值 value:中序遍历数组index
        indexMap = new HashMap<>(16);
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0, n - 1);
    }

}
