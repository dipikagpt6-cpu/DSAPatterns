package com.practice.TwoPointer;

import java.util.Arrays;

public class RearrangeZerosOnesAndTwosUnsorted {

    static void main() {
        int[] arr = {1,0,1,2,0,2,2,0,0,2,1,2,2,1};
        reArrangeZerosAndOnes(arr);
        System.out.println("Resultant Array:"+ Arrays.toString(arr));
    }

    private static void reArrangeZerosAndOnes(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (mid <= high) {
            if(arr[mid] == 0){
                int temp = arr[mid];
                arr[mid] = arr[low];
                arr[low] = temp;
                mid++;
                low++;
            }
            else if(arr[mid] == 1){
                mid++;
            }else{
                int temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;
                high --;
            }
        }
    }
}
