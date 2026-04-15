package com.practice.tree;

import java.util.*;


public class SerializeDeserializeBinaryTree {

    // 🔹 Serialize
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }

        sb.append(node.val).append(",");
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // 🔹 Deserialize
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();

        if (val.equals("null")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }

    // 🔹 Helper: Print tree (Preorder)
    public void printPreorder(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // 🔹 MAIN METHOD
    public static void main(String[] args) {

        SerializeDeserializeBinaryTree obj = new SerializeDeserializeBinaryTree();

        /*
                1
               / \
              2   3
                 / \
                4   5
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // 🔸 Serialize
        String serialized = obj.serialize(root);
        System.out.println("Serialized: " + serialized);

        // 🔸 Deserialize
        TreeNode newRoot = obj.deserialize(serialized);

        // 🔸 Print tree to verify
        System.out.print("Deserialized Tree (Preorder): ");
        obj.printPreorder(newRoot);
    }
}