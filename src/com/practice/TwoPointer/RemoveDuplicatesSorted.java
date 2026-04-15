package com.practice.TwoPointer;

public class RemoveDuplicatesSorted {

    static void main() {
        int[] arr = {0, 0, 0, 5, 5, 6, 7, 10};
        int resultSize = removeduplicates(arr);
        System.out.println("Result size of array:" + resultSize);
        //System.out.println("Resultant Array:"+ Arrays.toString(arr));
        for(int i = 0; i < resultSize; i++){
            System.out.println(arr[i]);
        }
    }

    private static int removeduplicates(int[] arr) {
        int k = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i-1] != arr[i]){
                arr[k] = arr[i];
                k++;
            }
        }
        return k;

    }
}
