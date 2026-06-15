package com.practice.Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.PriorityQueue;

/*
MAx heap
Like task scheduler 2
 */
public class RearrangeStringSoAsNotToHaveDuplicatesTogether {

    static void main() {
        String str = "aab";
        System.out.println(getRearrangedString(str));

    }

    private static String getRearrangedString(String str) {

        if (str == null || str.length() == 0) {
            return "";
        }

        if (str.length() == 1) {
            return str;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> b.freq - a.freq);
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch : str.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        int maxFreq = 0;
        for(int value : freqMap.values()){
            maxFreq = Math.max(maxFreq, value);
        }
        //isRearrangePossible?
        if(maxFreq > (str.length() + 1) /2){
            return "";
        }

        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        StringBuilder newStr = new StringBuilder();
        Pair prev = null;

        while(!pq.isEmpty()){

            Pair curr = pq.poll();
            newStr.append(curr.ch);
            curr.freq --;
            if(prev != null && prev.freq > 0){
                pq.offer(prev);
            }
            prev = curr;
        }
        return newStr.length() == str.length()? newStr.toString() : "";

    }

    static class Pair{
        int freq;
        char ch;
        Pair(char ch, int freq){
            this.ch= ch;
            this.freq = freq;
        }
    }




}
