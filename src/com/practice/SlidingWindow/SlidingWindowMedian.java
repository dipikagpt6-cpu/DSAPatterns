package com.practice.SlidingWindow;

import java.util.Arrays;

public class SlidingWindowMedian {

    /*
    you need the sorting
    median for odd no's is middle one
    median for even no's is avg of two median.

    Sort k elements = O(k log k)

Total windows = (n-k+1)

Overall:
O((n-k+1) * k log k)
     */

        public static void main(String[] args) {

            int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
            int k = 3;

            double[] result = findSlidingWindowMedian(nums, k);

            System.out.println(Arrays.toString(result));
        }

        private static double[] findSlidingWindowMedian(int[] nums, int k) {

            int n = nums.length;
            double[] result = new double[n - k + 1];

            for (int i = 0; i <= n - k; i++) {

                int[] window = new int[k];

                // Copy current window
                for (int j = 0; j < k; j++) {
                    window[j] = nums[i + j];
                }

                Arrays.sort(window);

                // Find median
                if (k % 2 == 1) {
                    result[i] = window[k / 2];
                } else {
                    result[i] =
                            ((double) window[k / 2 - 1] + window[k / 2]) / 2.0;
                }
            }

            return result;
        }
    }
