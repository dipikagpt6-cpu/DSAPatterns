package com.practice.SlidingWindow;

import java.util.Arrays;

public class MaxSubarraySumOfSizeK {

    static void main() {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int sum = findMaxSumOfSubrrayOfSizeK(arr, 3);
        System.out.println(sum);
    }

    private static int findMaxSumOfSubrrayOfSizeK(int[] arr, int k) {
        int maxSum = 0;
        int currentSum =0;
        int low = 0;
        while(low < k){
            currentSum+=arr[low];
            low++;
        }
        for(low = k ; low < arr.length ;low++ ){
            currentSum += arr[low];
            currentSum -= arr[low-k];
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}