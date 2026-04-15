package com.practice.TwoPointer;

import java.util.Arrays;

public class TwoSumConstantSpace {
    static void main() {
        int[] arr = {4,7,2,5,1,3,6,2};
        int k = 11;
        int[] result = findTwoSum(arr, k);
        System.out.println("Result : " +Arrays.toString(result));
    }

    private static int[] findTwoSum(int[] arr, int k) {
        Arrays.sort(arr);
        int[] result =  new int[2];
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            if(arr[left] + arr[right] > k){
                right --;
            }
            else if(arr[left] + arr[right] < k){
                left ++;
            }
            else{
                result[0] = arr[left];
                result[1] = arr[right];
                return result;
            }
        }
        return result;
    }
}
