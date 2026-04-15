package com.practice.tree;

public class DiameterOfBinaryTree {
    static int diameter = 0;
    static void main() {
   /*
                1
               / \
              2   3
             / \
            4   5

        Diameter = 3 (path: 4 → 2 → 1 → 3)
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int result = diameterOfBinaryTree(root);
        System.out.println("Diameter (edges): " + result);
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0; // reset for safety
        height(root);
        return diameter;
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;

        int left = height(node.left);
        int right = height(node.right);

        // update diameter
        diameter = Math.max(diameter, left + right);
        //height
        return 1 + Math.max(left, right);
    }

}
