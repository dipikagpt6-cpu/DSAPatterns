package com.practice.PrefixSum;

public class FindPivot {

    //find the index where prefix sum == suffix sum

    static void main() {
        int[] arr= {1, 7, 3, 4, 2, 2};
        System.out.println(getPivot(arr));
    }

    private static int getPivot(int[] arr) {

        int left = 0;
        int right =0;
        int sum = 0;
        for(int i =0; i < arr.length; i ++){
            sum += arr[i];
        }
        for(int i = 1; i < arr.length; i++){
            left += arr[i-1];
            right = sum - (left + arr[i]);
            if(left == right)
                return i;
        }
        return -1;
    }
}
