package com.janet.class05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2022/7/25
 * Time: 22:19
 *
 * @author jimas
 */
public class TreePrint {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, null, null);
        final TreeNode leftTwoLevel = new TreeNode(2, null, null);
        TreeNode leftOneLevel = new TreeNode(3, new TreeNode(2, leftTwoLevel, new TreeNode(1, null, null)), new TreeNode(11, null, null));
        root.left = new TreeNode(5, leftOneLevel, new TreeNode(4, null, null));
        TreeNode rightOneLevel = new TreeNode(18, new TreeNode(19, null, null), new TreeNode(20, null, null));
        root.right = new TreeNode(11, rightOneLevel, new TreeNode(14, new TreeNode(15, null, null), new TreeNode(16, null, null)));
        List<List<Integer>> result = printTreeLevelVal(root);

        System.out.println(result);
    }

    private static List<List<Integer>> printTreeLevelVal(TreeNode root) {
        List<List<Integer>> rs = new LinkedList<>();
        if (root == null) {
            return rs;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            List<Integer> sub = new ArrayList<>();
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                sub.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            rs.add(0,sub);

        }
        return rs;
    }
}
