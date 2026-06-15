package com.practice.topologicalSort;

import java.util.*;

public class CompilationOrder {

    /*
    CourseScheduleII

     */

    static void main() {
        Character[][] arr = {{'C', 'A'}, {'B', 'A'}, {'D', 'C'}, {'E', 'B'}, {'E', 'D'}};
        System.out.println("CompilationOrder:" + getCompilationOrder(arr));
    }

    private static List<Character> getCompilationOrder(Character[][] arr) {

        Map<Character, List<Character>> adjList = new HashMap<>();
        for(int i = 0 ; i < arr.length; i ++){
            Character from = arr[i][0];
            Character to = arr[i][1];

            adjList.putIfAbsent(from,
                    new ArrayList<>());

            adjList.get(from).add(to);

            // Ensure destination node exists
            adjList.putIfAbsent(to,
                    new ArrayList<>());
        }

        Map<Character, Integer> indegree = new HashMap<>();
        for(Character node : adjList.keySet()){
            indegree.putIfAbsent(node, 0);

            for(Character neigh : adjList.get(node)){
                indegree.put(neigh, indegree.getOrDefault(neigh, 0)+1);
            }
        }

        Queue<Character> queue = new LinkedList<>();
        List<Character> result = new ArrayList<>();
        for(Character ch : indegree.keySet()){
            if(indegree.get(ch) == 0){
                queue.add(ch);
            }
        }
        while(!queue.isEmpty()){
            Character node = queue.poll();
            result.add(node);
            List<Character> neighbors = adjList.getOrDefault(node, Collections.emptyList());
            for(Character neigh : neighbors){
                indegree.put(neigh, indegree.get(neigh)-1);
                if(indegree.get(neigh) == 0){
                    queue.add(neigh);
                }
            }


        }
        if(result.size() == indegree.size()){
            return result;
        }
        else return new ArrayList<>();


    }

}
