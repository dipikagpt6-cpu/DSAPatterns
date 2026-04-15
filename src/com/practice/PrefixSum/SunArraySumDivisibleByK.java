package com.practice.PrefixSum;

import java.util.HashMap;

public class SunArraySumDivisibleByK {
    static void main() {

        int[] arr= {1,2,3,2};
        System.out.println(getNoOfSubarrayWithDivisibleByK(arr, 2));

    }

    private static int getNoOfSubarrayWithDivisibleByK(int[] arr, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count=0;

        for(int i =0; i < arr.length; i++){
            sum += arr[i];
            int mod = (sum % k +k)%k;

            if(map.containsKey(mod)){
                count += map.get(mod);
            }
            map.put(mod, map.getOrDefault(mod, 0)+1);

        }
        return count;
    }
}
