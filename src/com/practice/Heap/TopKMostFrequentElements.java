package com.practice.Heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKMostFrequentElements {

    static void main() {
        int[]  arr = {1,2,4,2,3,4,4,8,8,8};
        System.out.println(Arrays.toString(getTopKElemts(arr, 4)));
    }

    private static int[] getTopKElemts(int[] arr, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : arr){
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        //minheap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        for(int n : map.keySet()){
            pq.add(n);
            if(pq.size() >k){
                pq.poll();
            }
        }
        int[] result = new int[k];
        int i =0;
        while(!pq.isEmpty()){
          result[i] = (pq.poll());
          i++;
        }

return result;
    }
}
