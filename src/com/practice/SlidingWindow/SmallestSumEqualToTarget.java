package com.practice.SlidingWindow;

import java.util.ArrayList;
import java.util.List;

public class SmallestSumEqualToTarget {

    static void main() {
        int[] arr = {3,5,2,8,2,9,10,1,5,1};
        List<Integer> result =  getSubarraySumEqualToTarget(arr, 7);
        System.out.println(result);
    }

    private static List<Integer> getSubarraySumEqualToTarget(int[] arr, int target) {

        int start =0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        List<Integer> result = new ArrayList<>();
        for(int end = 0 ; end < arr.length; end++) {
            sum += arr[end];

            while (sum > target) {
                sum -= arr[start];
                start++;
            }
            if (sum == target) {
                if (minLength > end - start + 1) {
                    minLength = end - start + 1;
                    result = new ArrayList<>();
                    for (int i = start; i <= end; i++) {
                        result.add(arr[i]);
                    }
                }
            }
        }
      return  result;
    }
}
