package com.practice.TwoPointer;

import java.util.Arrays;

public class RearrangeZerosAndOnesUnsorted {

    static void main() {
        int[] arr = {1,0,1,0,0,0,0,1,1,1};
        reArrangeZerosAndOnes(arr);
        System.out.println("Resultant Array:"+ Arrays.toString(arr));
    }

    private static void reArrangeZerosAndOnes(int[] arr) {
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            if(arr[left] == 0)
                left++;
            else if(arr[right] == 1)
                right--;
            else{
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }
}
