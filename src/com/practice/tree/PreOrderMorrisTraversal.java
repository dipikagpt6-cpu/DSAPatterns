package com.practice.tree;

import java.util.ArrayList;
import java.util.List;

public class PreOrderMorrisTraversal {

    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(preorderTraversal(root));
    }
    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {

            if (curr.left == null) {

                result.add(curr.val);
                curr = curr.right;

            } else {

                TreeNode pred = curr.left;

                while (pred.right != null &&
                        pred.right != curr) {

                    pred = pred.right;
                }

                if (pred.right == null) {

                    result.add(curr.val); // preorder visit

                    pred.right = curr;
                    curr = curr.left;

                } else {

                    pred.right = null;
                    curr = curr.right;
                }
            }
        }

        return result;
    }
}
