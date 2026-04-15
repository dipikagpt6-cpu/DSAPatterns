package com.practice.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletsToZeroAllPairsNoDuplicatePairs {

    static void main() {
        int[] arr  = {-1,0,1,1,2,3,-2,-3,1,0,1,1,-1,-1,2};
        List<int[]> result = getAllTriplets(arr);
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));

        }
    }

    private static List<int[]> getAllTriplets(int[] arr) {
    List<int[]> result = new ArrayList<>();
    Arrays.sort(arr);
    int i = 0;
    int left = i+1;
    int right = arr.length - 1;
    while(i<=arr.length-3 && left < right){
        if(arr[left] + arr[right] == -arr[i]){
         int[] triplets = {arr[i], arr[left], arr[right]};
         result.add(triplets);
         left++;
         right--;
         while (left < right && arr[left] == arr[left - 1]) left++;
         while (left < right && arr[right] == arr[right + 1]) right--;
        }
        else if(arr[left] + arr[right] > -arr[i]){
            right--;
        }else{
            left++;
        }
        i++;
    }
    return result;
    }
}
