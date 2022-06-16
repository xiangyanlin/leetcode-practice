package org.example.xyl.swordfingeroffer.bt;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 * @author xiangyanlin
 * @date 2022/6/16
 */
public class MaxDepth {


    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return getBTlevel(root);
    }

    public int getBTlevel(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int lLevel = getBTlevel(root.left);
        int rLevel = getBTlevel(root.right);
        return Math.max(lLevel, rLevel) + 1;
    }

    /**
     *剑指 Offer 55 - II. 平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isBalance(root).isBalanced;
    }

    public Info isBalance(TreeNode root) {
        if(root == null) {
            return new Info(0, true);
        }
        Info leftInfo = isBalance(root.left);
        Info rightInfo = isBalance(root.right);
        int high = Math.max(leftInfo.high, rightInfo.high) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.high - rightInfo.high) <= 1;
        return new Info(high, isBalanced);
    }

    static class Info {
        public Info(int high, boolean isBalanced) {
            this.high = high;
            this.isBalanced = isBalanced;
        }

        int high;
        boolean isBalanced;
    }
}
