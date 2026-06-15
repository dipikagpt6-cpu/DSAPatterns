package com.practice.Graph;

import java.util.*;

public class WordLadder {
    /*
    Almost like open lock
     Jab shortest path chahiye aur edge cost same ho, tab BFS best hota hai.
     */

    static void main() {
        List<String> wordList = List.of(("hot"),("dot"),("dog"),("lot"),("log"), ("cog"));
        String start = "hit";
        String end = "cog";
        System.out.println(getNoOfStepsToFormLadder(wordList, start, end));
    }

    private static int getNoOfStepsToFormLadder(List<String> wordList, String start, String end) {

        int steps = 0;
        //for O(1) lookup to disctionary
        Set<String> dictionary =  new HashSet<>();
        dictionary.addAll(wordList);

        if(end == null || start == null || wordList.isEmpty()){
            return 0;

        }
        if(end.equals(start)){
            return 1;
        }
        if(!dictionary.contains(end)){
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int k = 0 ; k < size; k++){
                String curr = queue.poll();
                char[] charArr = curr.toCharArray();
                for(int i = 0; i < charArr.length; i++){
                    char actual = charArr[i];
                    for(char ch = 'a'; ch <='z'; ch++){
                       if(ch == actual){
                           continue;
                       }
                       charArr[i] = ch;
                       String newString = new String(charArr);
                        if (dictionary.contains(newString)
                                && !visited.contains(newString)) {

                            visited.add(newString);
                            queue.offer(newString);
                        }
                    }
                    charArr[i] = actual;

                }

            }
            steps++;

        }
return steps;

    }
}
