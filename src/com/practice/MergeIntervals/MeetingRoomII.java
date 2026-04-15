package com.practice.MergeIntervals;


import java.util.Arrays;
import java.util.PriorityQueue;

//minimum rooms required
public class MeetingRoomII {

    static void main() {


        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}};

        System.out.println("Rooms needed (Test 1): " + minMeetingRooms(intervals1)); // 2
        System.out.println("Rooms needed (Test 2): " + minMeetingRooms(intervals2)); // 1
    }

    private static int minMeetingRooms(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0] ));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(intervals[0][1]);

        for(int i = 1; i < intervals.length ; i ++ ){
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll(); // room freed
            }

            // ALWAYS add current meeting
            minHeap.offer(intervals[i][1]);
        }

        return minHeap.size();



    }

}
