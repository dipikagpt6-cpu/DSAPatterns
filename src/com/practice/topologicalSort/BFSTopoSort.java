package com.practice.topologicalSort;


//Kahn’s Algorithm
import java.util.*;

public class BFSTopoSort {

    public static List<Integer> topologicalSort(
            Map<Integer, List<Integer>> graph) {

        // Step 1: Calculate indegree of each node
        Map<Integer, Integer> indegree = new HashMap<>();

        // Initialize all nodes
        for (int node : graph.keySet()) {
            indegree.putIfAbsent(node, 0);

            for (int neighbor : graph.get(node)) {
                indegree.put(neighbor,
                        indegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        // Step 2: Add nodes with indegree 0
        Queue<Integer> queue = new LinkedList<>();

        for (int node : indegree.keySet()) {
            if (indegree.get(node) == 0) {
                queue.offer(node);
            }
        }

        // Step 3: BFS
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {

            int current = queue.poll();

            result.add(current);

            // Get neighbors safely
            List<Integer> neighbors =
                    graph.getOrDefault(current,
                            Collections.emptyList());

            for (int neighbor : neighbors) {

                indegree.put(neighbor,
                        indegree.get(neighbor) - 1);

                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: Detect cycle
        if (result.size() != indegree.size()) {
            throw new RuntimeException("Cycle detected");
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