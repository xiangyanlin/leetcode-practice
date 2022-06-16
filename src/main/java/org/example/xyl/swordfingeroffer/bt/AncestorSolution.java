package org.example.xyl.swordfingeroffer.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyanlin
 * @date 2022/6/16
 */
public class AncestorSolution {

    List<TreeNode> list = new ArrayList<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == null) {
            return q;
        }
        if (q == null) {
            return p;
        }
        //中序遍历排序
        dfs(root);
        int pIndex = list.indexOf(p);
        int qIndex = list.indexOf(q);
        //遍历找到共同祖先
        return null;
    }

    public void dfs(TreeNode root) {
        dfs(root.left);
        list.add(root);
        dfs(root.right);
    }
}
