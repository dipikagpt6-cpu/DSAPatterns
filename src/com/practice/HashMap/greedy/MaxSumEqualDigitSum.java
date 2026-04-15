package com.practice.HashMap.greedy;

import java.util.HashMap;

public class MaxSumEqualDigitSum {

    static void main() {

        int[] arr = {18,43, 36,13,7};
        System.out.println(getMaxSum(arr));
    }

    private static int getMaxSum(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int maxSum =0;
        for(int i = 0 ; i < arr.length; i++){
            int sum = getSumOfDigits(arr[i]);
            if(map.containsKey(sum)){
                maxSum = Math.max((map.get(sum)+arr[i]), maxSum);
                map.put(sum, Math.max(map.get(sum), arr[i]) );
            }else{
                map.put(sum, arr[i]);
            }
        }
        return maxSum;

    }
    private static int getSumOfDigits(int n){
        int sum = 0;
        while(n > 0){
            sum += n%10;
            n /=10;
        }
        return sum;
    }
}
