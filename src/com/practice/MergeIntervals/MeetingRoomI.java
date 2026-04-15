package com.practice.MergeIntervals;


import java.util.Arrays;

//Can a person attend all meeting? If not overlaps
public class MeetingRoomI {

    static void main() {
        int[][] arr = {{0, 30}, {40,45}};
        System.out.println(canAttendMeeting(arr));
    }

    private static boolean canAttendMeeting(int[][] arr) {

        Arrays.sort(arr, (a,b) -> a[0] -b[0]);
        for (int i = 1; i < arr.length; i++) {
            int currentStart = arr[i][0];
            int currentEnd = arr[i][1];
            int prevStart = arr[i-1][0];
            int prevEnd = arr[i-1][1];
            if(currentStart < prevEnd){
                return false;
            }

        }
        return true;
    }


}
