package com.practice.tree;

public class isSubTree {

    static void main() {
           /*
                    3
                   / \
                  4   5
                 / \
                1   2
         */

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(4);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        /*
                  4
                 / \
                1   2
         */

        TreeNode subRoot = new TreeNode(4);

        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        boolean result = isSubtree(root, subRoot);

        System.out.println("Is Subtree: " + result);
    }

    private static boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if(root == null){
            return false;
        }
        if(subRoot == null) {
            return true;
        }

        if(isSameTree(root, subRoot)){
            return true;
        }

        return (isSubtree(root.left, subRoot.left)
                || isSubtree(root.right, subRoot.right));

    }

    private static boolean isSameTree(TreeNode root, TreeNode subRoot) {

        if(root == null && subRoot == null){
            return true;
        }
        if(root == null || subRoot == null){
            return false;
        }
        if(root.val != subRoot.val){
            return false;
        }
        return isSameTree(root.left, root.left) && isSameTree(root.right,subRoot.right);
    }


}
