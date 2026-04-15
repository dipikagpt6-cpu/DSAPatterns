package com.practice.Heap;

import java.util.*;

public class TaskScheduler_II {

    static class Task{
        int availableTime;
        int count;
        Task(int  availableTime, int count)
        {
            this.availableTime = availableTime;
            this.count = count;
        }
    }

    static void main() {
        Character[] arr = {'A','A'};
        System.out.println(getMinTime(arr,1));
    }

    private static int getMinTime(Character[] arr, int space) {
         Map<Character, Integer> freqMap = new HashMap<>();
        for (Character character : arr) {
            freqMap.put(character, freqMap.getOrDefault(character, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(freqMap.values());

        Queue<Task> queue = new LinkedList<>();
        int time =0;
        while(!maxHeap.isEmpty() || !queue.isEmpty()){
            time++;
            if(!maxHeap.isEmpty()){
                int count = maxHeap.poll() -1;
                if(count > 0){
                    queue.offer(new Task(time+space, count));
                }
            }
            if(!queue.isEmpty() && queue.peek().availableTime == time){
                maxHeap.offer(queue.poll().count);
            }
        }

return time;

    }
}
