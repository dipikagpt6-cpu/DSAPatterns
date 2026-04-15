package com.practice.SlidingWindow;

import java.util.ArrayList;
import java.util.List;

public class SmallestSubarrayWithSumGreaterOrEqualToTarget {
    public static void main(String[] args) {
        int[] arr = {4,3,1,9,5,3,9,10,4};
        List<Integer> result = smallestSubarrayWithGiveSum(arr, 18);
        System.out.println(result);
    }

    private static List<Integer> smallestSubarrayWithGiveSum(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();

        int minLength = Integer.MAX_VALUE;
        int start = 0;
        int currentSum = 0;

        for (int end = 0; end < arr.length; end++) {
            currentSum += arr[end];

            while (currentSum >= k) {
                if (end - start + 1 < minLength) {
                    minLength = end - start + 1;

                    // clear old result
                    result.clear();

                    // store new subarray
                    for (int i = start; i <= end; i++) {
                        result.add(arr[i]);
                    }
                }

                currentSum -= arr[start];
                start++;
            }
        }

        return result;
    }
}