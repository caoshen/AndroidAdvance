package com.example.androidadvance.chap2.filescan;

import java.util.ArrayDeque;

/**
 * @author caoshen
 * @date 2020/9/10
 */
public class Search {

    /**
     * DFS
     * using array deque
     *
     * @param root tree root
     */
    public void depthOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.value);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }
}
