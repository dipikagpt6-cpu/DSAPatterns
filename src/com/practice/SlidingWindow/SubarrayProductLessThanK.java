package com.practice.SlidingWindow;

import java.util.ArrayList;
import java.util.List;

public class SubarrayProductLessThanK {

    static void main() {
        int[] arr = {10,5,6,2};
        List<List<Integer>> list = getSubarray(arr, 100);
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }

    }

    private static List<List<Integer>> getSubarray(int[] arr, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int start = 0;
        int product = 1;
        int maxLen = Integer.MIN_VALUE;
        for(int end = 0; end < arr.length; end++){
            product *= arr[end];
            while(product >= k){
                int leftInt = arr[start];
                product /= arr[start];
                start++;
            }
            List<Integer> temp = new ArrayList<>();
            for (int i = end; i >= start; i--) {
                temp.add(0, arr[i]); // insert at beginning
                result.add(new ArrayList<>(temp));
            }

        }
        return result;

    }

}
