package com.practice.KadanesAlgo;

public class MaxSubarraySum {
    //handles negative numbers also
    static void main() {
        int[] arr ={-2,-5,-4};
        System.out.println(getMaxSumSubarray(arr));

    }

    private static int getMaxSumSubarray(int[] arr) {

        int currentSum = arr[0];
        int maxSum = arr[0];
        for(int i = 1; i < arr.length; i ++){
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);

        }
        return maxSum;

    }
}
