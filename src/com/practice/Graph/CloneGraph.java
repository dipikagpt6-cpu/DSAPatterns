package com.practice.Graph;
import java.util.*;

public class CloneGraph {

    static class Node {
        int val;
        List<Node> neighbors;

        Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    }

    private static Map<Node, Node> map = new HashMap<>();

    public static Node cloneGraph(Node node) {

        if (node == null)
            return null;

        // Already cloned
        if (map.containsKey(node))
            return map.get(node);

        // Create clone
        Node clone = new Node(node.val);

        // Store immediately (important for cycles)
        map.put(node, clone);

        // Clone neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }

    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

        n3.neighbors.add(n2);
        n3.neighbors.add(n4);

        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        Node clone = cloneGraph(n1);

        System.out.println("Original Node: " + n1.val);
        System.out.println("Cloned Node: " + clone.val);

        System.out.println("Same Object? " + (n1 == clone));
    }
}
