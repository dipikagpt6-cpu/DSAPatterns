package com.practice.TwoPointer;

import java.util.Arrays;

public class TripletsToClosestSum {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 1, 2};
        int result = getClosestSum(arr, 5);
        System.out.println(result);
    }

    private static int getClosestSum(int[] arr, int target) {
        Arrays.sort(arr);

        int closestSum = arr[0] + arr[1] + arr[2];

        for (int i = 0; i < arr.length - 2; i++) {

            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == target) {
                    return sum;
                }

                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }
}