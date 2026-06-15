package com.practice.topologicalSort;

import java.util.*;

public class DFSToposort {

    private static void dfs(
            int node,
            Map<Integer, List<Integer>> graph,
            Set<Integer> visited,
            Stack<Integer> stack) {

        visited.add(node);

        // Visit all neighbors
        for (int neighbor :
                graph.getOrDefault(node,
                        Collections.emptyList())) {

            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, stack);
            }
        }

        // Push after visiting neighbors
        stack.push(node);
    }

    public static List<Integer> topologicalSort(
            Map<Integer, List<Integer>> graph) {

        Set<Integer> visited = new HashSet<>();

        Stack<Integer> stack = new Stack<>();

        // Collect all nodes
        Set<Integer> allNodes = new HashSet<>();

        for (int node : graph.keySet()) {

            allNodes.add(node);

            for (int neighbor : graph.get(node)) {
                allNodes.add(neighbor);
            }
        }

        // Run DFS for all nodes
        for (int node : allNodes) {

            if (!visited.contains(node)) {
                dfs(node, graph, visited, stack);
            }
        }

        // Build result
        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public static void main(String[] args) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(5, Arrays.asList(2, 0));
        graph.put(4, Arrays.asList(0, 1));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(1));

        List<Integer> order = topologicalSort(graph);

        System.out.println("Topological Order: " + order);
    }
}