package org.example.xyl.swordfingeroffer.bt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 *
 * @author xiangyanlin
 * @date 2022/6/16
 */
public class AncestorSolution {

    Queue<TreeNode> queue = new LinkedList<>();
    Map<Integer, TreeNode> map = new HashMap<>();
    Set<TreeNode> set = new HashSet<>();

    /**
     * 二叉搜索树的最近公共祖先
     */
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
        queue.add(root);
        map.put(root.val, null);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                map.put(node.left.val, node);
                queue.add(node.left);
            }
            if (node.right != null) {
                map.put(node.right.val, node);
                queue.add(node.right);
            }
        }
        while (p != null) {
            set.add(p);
            p = map.get(p.val);
        }
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = map.get(q.val);
        }
        //遍历找到共同祖先
        return null;
    }

    /**
     * 官方答案  遍历一次
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestorNoSearch(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q).ancestor;
    }

    public Info dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return new Info(null, false, false);
        }

        Info leftInfo = dfs(root.left, p, q);
        Info rightInfo = dfs(root.right, p, q);

        TreeNode ancestor = null;
        boolean findP = (root == p || leftInfo.findP || rightInfo.findP);
        boolean findQ = (root == q || leftInfo.findQ || rightInfo.findQ);
        if(leftInfo.ancestor != null ) {
            ancestor = leftInfo.ancestor;
        }
        if(rightInfo.ancestor != null ) {
            ancestor = rightInfo.ancestor;
        }
        if (ancestor == null) {
            if (findP && findQ) {
                ancestor = root;
            }
        }
        return new Info(ancestor, findP, findQ);
    }


    public static class Info {
        public TreeNode ancestor;
        public boolean findP;
        public boolean findQ;

        public Info(TreeNode ancestor, boolean findP, boolean findQ) {
            this.ancestor = ancestor;
            this.findP = findP;
            this.findQ = findQ;
        }
    }


}
