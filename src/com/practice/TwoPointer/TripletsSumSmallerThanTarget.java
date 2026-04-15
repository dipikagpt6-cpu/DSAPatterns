package com.practice.TwoPointer;

import java.util.Arrays;

public class TripletsSumSmallerThanTarget {

    public static void main(String[] args) {
        int[] arr = {-2, 0, 1, 3};
        int result = getClosestSum(arr, 2);
        System.out.println(result);
    }

    private static int getClosestSum(int[] arr, int target) {
        Arrays.sort(arr);
        int result = 0;
        for(int i = 0; i < arr.length-2; i ++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum < target) {
                    result = result + (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}