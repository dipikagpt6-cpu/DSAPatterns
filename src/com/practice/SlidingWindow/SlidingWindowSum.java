package com.practice.SlidingWindow;

import java.util.Arrays;

public class SlidingWindowSum {

    /*
    Given an integer array nums and a window size k,
    return the sum of every contiguous subarray of size k.
     */
    /*
    newSum = oldSum - outgoingElement + incomingElement
     */

    public static void main() {
        int[] arr = {1,2,3,4,5};
        int k = 3;
        System.out.println(Arrays.toString(getWindowSum(arr, k)));
    }

    private static int[] getWindowSum(int[] arr, int k) {

        if(arr == null || k > arr.length){
            return new int[0];

        }
        int n = arr.length;
        int[] result = new int[n-k +1];
        int idx = 0;
        int windowSum = 0;

        for(int i =0 ;i < k;i++){
            windowSum += arr[i];
        }
        result[idx++] = windowSum;

        for(int i = k; i < n ; i++){
            windowSum = windowSum - arr[i-k] + arr[i];
            result[idx++] = windowSum;
        }

return result;
    }
}
