package org.example.xyl.swordfingeroffer.bt;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * @author xiangyanlin
 * @date 2022/6/13
 */
public class TreeToDoublyListSolution {

    Queue<Node> nodes = new LinkedList<>();
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }
        //中序遍历
        midDfs(root);

        //设置节点
        Node res = null;
        Node pre = null;
        while (!nodes.isEmpty()) {
            Node cur = nodes.poll();
            cur.left = pre;
            cur.right = nodes.peek();
            pre = cur;
            if (res == null ) {
                res =cur;
            }
            if(nodes.peek() == null ) {
                cur.right = res;
                res.left = cur;
            }
        }
        return res;
    }


    /**
     * 中序遍历
     * @param root
     */
    public void midDfs(Node root) {
        if(root == null) {
            return;
        }
        midDfs(root.left);
        nodes.add(root);
        midDfs(root.right);
    }

    Node pre, head;
    public Node treeToDoublyList2(Node root) {
        if(root == null) {
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(Node cur) {
        if(cur == null) {
            return;
        }

        dfs(cur.left);

        if(pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;

        dfs(cur.right);
    }




    public static void main(String[] args) {
        TreeToDoublyListSolution test = new TreeToDoublyListSolution();
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(5);
        head.left.left = new Node(1);
        head.left.right = new Node(3);


        Node node = test.treeToDoublyList(head);
        System.out.println("========");
    }


}
