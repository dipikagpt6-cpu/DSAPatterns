package com.practice.KadanesAlgo;

public class MaxSubarrayProduct {
    //handles negative numbers also

    static void main() {
        int[] arr ={-2,5,-4};
        System.out.println(getMaxProductSubarray(arr));

    }

    private static int getMaxProductSubarray(int[] arr) {

        int maxProduct = arr[0];
        int minProduct = arr[0];
        int result = arr[0];
        for(int i = 0 ; i < arr.length; i++){
           int temp = maxProduct;
           maxProduct = Math.max(arr[i], Math.max(maxProduct * arr[i] , minProduct * arr[i]));
           minProduct = Math.min(arr[i], Math.min(temp *arr[i], minProduct*arr[i]));
           result = Math.max(result, maxProduct);
        }
        return result;
    }
}
