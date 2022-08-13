package com.janet.class05;

/**
 * Date: 2022/7/25
 * Time: 22:19
 *
 * @author jimas
 */
public class BalanceTreeCheck {
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
        root.left = new TreeNode(5, leftOneLevel, new TreeNode(4, new TreeNode(41, null, null), new TreeNode(42, null, null)));
        TreeNode rightOneLevel = new TreeNode(18, new TreeNode(19, null, null), new TreeNode(20, null, null));
        root.right = new TreeNode(11, rightOneLevel, new TreeNode(14, new TreeNode(15, null, null), new TreeNode(16, null, null)));

        Info info = process(root);
        System.out.println(info);
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance
                && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalance, height);
    }

    private static class Info {
        private boolean isBalance;
        private int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isBalance=" + isBalance +
                    ", height=" + height +
                    '}';
        }
    }


}
