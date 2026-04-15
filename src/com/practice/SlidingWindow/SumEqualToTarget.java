package com.practice.SlidingWindow;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.List;

public class SumEqualToTarget {

    static void main() {
        int[] arr = {3,5,2,8,2,9,10,1,6,1};
        List<Integer> result =  getSubarraySumEqualToTarget(arr, 8);
        System.out.println(result);
    }

    private static List<Integer> getSubarraySumEqualToTarget(int[] arr, int target) {

        int start =0;
        int sum = 0;
        for(int end = 0 ; end < arr.length; end++){
            sum+=arr[end];

            while(sum > target){
                sum -= arr[start];
                start++;
            }
            if(sum == target){
                List<Integer> result = new ArrayList<>();
                for(int i = start ; i <=end ; i++){
                    result.add(arr[i]);
                }
                return result;
            }
        }
      return new ArrayList<>();
    }
}
