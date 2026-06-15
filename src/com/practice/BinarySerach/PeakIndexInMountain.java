package com.practice.BinarySerach;

public class PeakIndexInMountain {

    static void main() {
        int arr[] = {1,2,3,4,3,3,5};
        System.out.println(getPeak(arr));
    }

    private static int getPeak(int[] arr) {
        int low  =0;
        int high = arr.length -1;
        while(low  < high){
            int mid = low + (high -low)/2;
            if(arr[mid] <= arr[mid +1]){
                low = mid +1 ;
            }else {
                high = mid;
            }
        }
return low;

    }
}
