package com.practice.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumMeetingRoom {

    static void main() {
        int[][] intervals = {
                {0,8},{5,10},{15,20}
        };

        System.out.println(minMeetingRooms(intervals));
    }

    private static int minMeetingRooms(int[][] intervals) {
//minheap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //sort by 0th index
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        pq.add(intervals[0][1]);
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= pq.peek()){
                pq.poll();
            }
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }
}
