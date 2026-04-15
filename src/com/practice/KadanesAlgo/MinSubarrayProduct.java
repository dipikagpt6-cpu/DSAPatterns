package com.practice.KadanesAlgo;

public class MinSubarrayProduct {
    //handles negative numbers also

    static void main() {
        int[] arr ={-2,5,-4};
        System.out.println(getMinProductSubarray(arr));

    }

    private static int getMinProductSubarray(int[] arr) {

        int maxProduct = arr[0];
        int minProduct = arr[0];
        int result = arr[0];
        for(int i = 1 ; i < arr.length; i++){
           int temp = minProduct;
           minProduct = Math.min(arr[i], Math.min(maxProduct *arr[i], minProduct*arr[i]));
           maxProduct = Math.max(arr[i], Math.max(maxProduct * arr[i] , temp * arr[i]));
           result = Math.min(result, minProduct);
        }
        return result;
    }
}
