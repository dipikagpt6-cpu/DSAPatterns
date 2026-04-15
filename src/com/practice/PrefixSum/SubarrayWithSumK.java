package com.practice.PrefixSum;

import java.util.HashMap;

public class SubarrayWithSumK {

    static void main() {

        int[] arr= {1,2,3,2};
        System.out.println(getNoOfSubarrayWithSumK(arr, 5));

    }

    private static int getNoOfSubarrayWithSumK(int[] arr, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count=0;
        for(int i =0; i < arr.length; i++){
            sum += arr[i];
            if(map.containsKey(sum-k)){
                count += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);

        }
        return count;
    }
}
