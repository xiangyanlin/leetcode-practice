package org.example.xyl.swordfingeroffer.bt;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * @author xiangyanlin
 * @date 2022/6/13
 */
public class KthLargestSolution {

    int res, k;
    public int kthLargest(TreeNode root, int k) {
        if(root == null) {
            return 0;
        }
        this.k = k;
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if(root == null || k == 0) {
            return ;
        }
        //反 中序遍历

        dfs(root.right);

        //业务逻辑
        if(--k == 0) {
            res = root.val;
        }

        dfs(root.left);
    }
}
