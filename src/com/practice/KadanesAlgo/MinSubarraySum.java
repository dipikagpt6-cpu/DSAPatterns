package com.practice.KadanesAlgo;

public class MinSubarraySum {
    //handles negative numbers also
    static void main() {
        int[] arr ={-2,-5,-4};
        System.out.println(getMinSumSubarray(arr));

    }

    private static int getMinSumSubarray(int[] arr) {

        int currentSum = arr[0];
        int minSum = arr[0];
        for(int i = 1; i < arr.length; i ++){
            currentSum = Math.min(arr[i], currentSum + arr[i]);
            minSum = Math.min(minSum, currentSum);

        }
        return minSum;

    }
}
