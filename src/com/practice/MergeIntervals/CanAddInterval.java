package com.practice.MergeIntervals;

import java.util.Arrays;

public class CanAddInterval {

    static void main() {
        int[][] arr = {{1,3}, {5,6}, {4,5}, {7,9}};
        int[] newArr = {9,10};
        System.out.println(canAdd(arr, newArr));
    }

    private static boolean canAdd(int[][] arr, int[] newArr) {

        Arrays.sort(arr, (a,b)-> (a[0] - b[0]));
        for(int  i = 0;i < arr.length; i++){
            int[] curr = arr[i];
            if(newArr[0] < curr[1] && newArr[1] > curr[0]){
                return false;
            }
        }
        return true;
    }
}
