package com.practice.TwoPointer;

public class TrappingTheRainWater {

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(getMaxWaterTrapped(arr)); // Output: 6
    }

    private static int getMaxWaterTrapped(int[] arr) {

        int start = 0;
        int end = arr.length - 1;

        int lmax = 0;
        int rmax = 0;

        int result = 0;

        while (start < end) {

            // Jo side chhoti hai wahi process hogi
            if (arr[start] < arr[end]) {

                // left max update
                if (arr[start] >= lmax) {
                    lmax = arr[start];
                } else {
                    // water trapped
                    result += lmax - arr[start];
                }

                start++;

            } else {

                // right max update
                if (arr[end] >= rmax) {
                    rmax = arr[end];
                } else {
                    // water trapped
                    result += rmax - arr[end];
                }

                end--;
            }
        }

        return result;
    }
}