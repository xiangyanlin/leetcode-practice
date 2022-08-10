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
     */
    public TreeNode myBuildTree(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft) {
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
        root.left = myBuildTree(preorder, preorderLeft + 1, preorderLeft + sizeLeftSubtree, inorderLeft);

        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素
        // 就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, preorderLeft + sizeLeftSubtree + 1, preorderRight, inorderRoot + 1);
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
        return myBuildTree(preorder, 0, n - 1, 0);
    }

    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap<>();
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for(int i = 0; i < inorder.length; i++) {
            dic.put(inorder[i], i);
        }
        return recur(0, 0, inorder.length - 1);
    }
    TreeNode recur(int root, int left, int right) {
        // 递归终止
        if(left > right) {
            return null;
        }
        // 建立根节点
        TreeNode node = new TreeNode(preorder[root]);
        // 划分根节点、左子树、右子树
        //在中序遍历中定位根节点的index  代表
        int i = dic.get(preorder[root]);
        // 开启左子树递归
        node.left = recur(root + 1, left, i - 1);
        // 1 0 0
        // 开启右子树递归 // (i - left)左子树长度

        //第一次到达root为最左节点 (i - left)左子树长度为0 +1到达右子树第一个节点
        node.right = recur(root + (i - left) + 1, i + 1, right);
        // 回溯返回根节点
        return node;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 2, 1, 7};
        int[] inorder = {9, 3, 1, 2, 7};
        SerializeTreeSolution solution = new SerializeTreeSolution();
        solution.buildTree2(preorder, inorder);
    }

}
