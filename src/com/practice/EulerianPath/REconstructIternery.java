package com.practice.EulerianPath;


import java.sql.SQLOutput;
import java.util.*;

/*
Eulerian Path
HierHolzer
DFS
PriorityQueue
minheap
 */
public class REconstructIternery {

    public static void main(String args[]) {

        List<List<String>> arr = List.of(
                List.of("JFK", "SFO"),
                List.of("JFK", "ATL"),
                List.of("SFO", "ATL"),
                List.of("ATL", "JFK"),
                List.of("ATL", "SFO")
        );

        System.out.println(getIternary(arr));

    }

    private static List<String> getIternary(List<List<String>> arr) {

        LinkedList<String> result = new LinkedList<>();

        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        Map<String, Integer> outdegree = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        for(List<String> iternery: arr){
            String from = iternery.get(0);
            String to = iternery.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);

            outdegree.put(from, outdegree.getOrDefault(from, 0)+1);
            indegree.put(to, indegree.getOrDefault(to, 0)+1);

        }
        String start = null;
        for(String node : graph.keySet()){
          int out =   outdegree.getOrDefault(node,0);
          int in = indegree.getOrDefault(node,0);
          if(out == in +1){
              start = node;
              break;
          }
        }
        if(start == null){
            start = graph.keySet().iterator().next();
        }
        dfs(start, graph, result);
        return result;

    }

    private static void dfs(String start, Map<String, PriorityQueue<String>> graph, LinkedList<String> result) {
      PriorityQueue<String> pq = graph.get(start);
      while(pq != null && !pq.isEmpty()){
          String node = pq.poll();
          dfs(node, graph, result);
      }
      result.addFirst(start);
    }

}
