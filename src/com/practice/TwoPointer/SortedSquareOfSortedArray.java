package com.practice.TwoPointer;

import java.util.Arrays;

public class SortedSquareOfSortedArray {

    static void main() {
        int[] arr = {-6,-4,-2,0,2,4,6,8};
        int[] result = findSortedSquareArray(arr);
        System.out.println("Resultant array:" + Arrays.toString(result));

    }

    private static int[] findSortedSquareArray(int[] arr) {

        int[] result = new int[arr.length];
        int left = 0;
        int right = arr.length -1;
        int j = arr.length-1;

        while(left < right && j >=0) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            if (leftSquare >= rightSquare) {
                result[j] = leftSquare;
                left++;
                j--;
            } else {
                result[j] = rightSquare;
                right--;
                j--;
            }
        }
        return result;
    }
}
