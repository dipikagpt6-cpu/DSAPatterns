package com.practice.topologicalSort;

import java.util.*;

public class AlienDictionary {

    public static String alienOrder(List<String> words) {

        Map<Character, List<Character>> adj =
                new HashMap<>();

        Map<Character, Integer> indegree =
                new HashMap<>();

        // Initialize all characters
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                adj.putIfAbsent(ch, new ArrayList<>());
                indegree.putIfAbsent(ch, 0);
            }
        }

        // Build graph
        for (int i = 0; i < words.size() - 1; i++) {

            String w1 = words.get(i);
            String w2 = words.get(i + 1);

            int len = Math.min(w1.length(), w2.length());

            for (int j = 0; j < len; j++) {

                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {

                    if (!adj.get(c1).contains(c2)) {
                        adj.get(c1).add(c2);
                        indegree.put(c2,
                                indegree.get(c2) + 1);
                    }

                    break;
                }
            }

            // Invalid case
            if (w1.length() > w2.length()
                    && w1.startsWith(w2)) {
                return "";
            }
        }

        // BFS
        Queue<Character> queue =
                new LinkedList<>();

        for (char ch : indegree.keySet()) {
            if (indegree.get(ch) == 0) {
                queue.add(ch);
            }
        }

        StringBuilder result =
                new StringBuilder();

        while (!queue.isEmpty()) {

            char node = queue.poll();

            result.append(node);

            for (char neigh : adj.get(node)) {

                indegree.put(neigh,
                        indegree.get(neigh) - 1);

                if (indegree.get(neigh) == 0) {
                    queue.add(neigh);
                }
            }
        }

        // Cycle check
        if (result.length() != indegree.size()) {
            return "";
        }

        return result.toString();
    }

    public static void main(String[] args) {

        List<String> words = Arrays.asList(
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        );

        System.out.println(alienOrder(words));
    }
}