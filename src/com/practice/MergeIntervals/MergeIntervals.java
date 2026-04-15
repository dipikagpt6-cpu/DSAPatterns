package com.practice.MergeIntervals;

import java.util.Arrays;

public class MergeIntervals {
    static void main() {
        int[][] arr = {{1,3}, {2,5}, {4,8}, {10,16}, {14,19}};
        int[][] result = getMergedIntervals(arr);
        for(int i = 0 ; i< result.length; i++){
            System.out.println(Arrays.toString(result[i]));
        }

    }

    private static int[][] getMergedIntervals(int[][] arr) {
        Arrays.sort(arr,(a,b) -> a[0] - b[0] );
        int[][] result = new int[arr.length][];
        result[0] = arr[0];
        //to track the result end element
        int index = 0;
        for(int i = 1 ; i < arr.length ; i++){
            int currentstart = arr[i][0];
            int currentEnd = arr[i][1];
            int previourEnd = result[index][1];
            if(previourEnd > currentstart){
                result[index][1] = Math.max(previourEnd, currentEnd);
            }else{
                index++;
                result[index] = arr[i];
            }

        }
        //so as not to return null places;
return Arrays.copyOf(result, index + 1);
    }


}
