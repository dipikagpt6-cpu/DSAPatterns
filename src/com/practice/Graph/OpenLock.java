package com.practice.Graph;

import java.util.*;

public class OpenLock {
/*
given deadends and target, find the number of steps to open lock
 */
    static void main() {
        String target = "0202";
        String[] deadends = {"0201","0101","0102","1212","2002"};
        System.out.println(getNoOfStepsToOpenLock(deadends, target));

    }

    private static int getNoOfStepsToOpenLock(String[] deadends, String target) {
        String start = "0000";
        Set<String> visited = new HashSet<>();
        Set<String> deadend = new HashSet<>(Arrays.asList(deadends));
        int steps = 0;
        if (deadend.contains(start)) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                String curr = queue.poll();

                // target mil gaya
                if (curr.equals(target)) {
                    return steps;
                }

                // saare neighbors nikalo
                for (String next : getNeighbours(curr)) {

                    if (!deadend.contains(next)
                            && !visited.contains(next)) {

                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
                steps ++;


        }
        return -1;
    }

    private static List<String> getNeighbours(String node){

        List<String> result =  new ArrayList<>();
        char[] ch = node.toCharArray();
        for(int i = 0; i < 4 ; i++){
            char c = ch[i];
            ch[i] = (char) (((c-'0')+1) % 10 + '0');
            result.add(new String(ch));

            ch[i] = (char) (((c-'0')+9) % 10 + '0');
            result.add(new String(ch));

            ch[i] = c;

        }
        return result;

    }
}
