package com.practice.KadanesAlgo;

public class MaxSubarraySumWithOneDeletion {

    static void main() {

        int[] arr = {-50,-2,0,-13};
        System.out.println(getMaxSum(arr));
    }

    private static int getMaxSum(int[] arr) {

        int result = arr[0];
        int keep = arr[0];
        int delete =0;
        for(int i = 1; i <arr.length; i++){
            int temp = keep;
            keep = Math.max(arr[i], arr[i]+ keep);
            delete = Math.max(delete+arr[i], temp);
            result = Math.max(result, Math.max(delete, keep));
        }
        return result;
    }
}
