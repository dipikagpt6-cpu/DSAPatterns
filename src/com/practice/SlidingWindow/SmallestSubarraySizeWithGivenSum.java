package com.practice.SlidingWindow;

public class SmallestSubarraySizeWithGivenSum {
    static void main() {
        int[] arr =  {4,3,1,9,5,3,9,10,4};
        int result = smallestSubarrayWithGiveSun(arr,18 );
        System.out.println(result);
    }

    private static int smallestSubarrayWithGiveSun(int[] arr, int k) {
        int result = Integer.MAX_VALUE;
        int start = 0;
        int currentSum = 0;
        for(int end = 0; end < arr.length ; end++){
            currentSum += arr[end];
            while(currentSum >= k){
                result = Math.min(result, end-start+1);
                currentSum -= arr[start];
                start++;
            }
        }

        return result;
    }

}
