package com.practice.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumReturnAllConstantSpace {
    static void main() {
        int[] arr = {4,7,2,5,1,3,6,2};
        int k = 11;
       List<int[]> result = findTwoSum(arr, k);
       for(int[] pair : result){
           System.out.println(Arrays.toString(pair));
       }

    }

    private static List<int[]>findTwoSum(int[] arr, int k) {
        Arrays.sort(arr);
        List<int[]> result = new ArrayList<>();
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
                result.add(new int[]{arr[left], arr[right]});
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1]) left++;
                while (left < right && arr[right] == arr[right + 1]) right--;
            }


        }
        return result;
    }
}
