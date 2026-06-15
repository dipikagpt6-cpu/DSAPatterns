package com.practice.BinarySerach;

public class UpperBound {

    static void main() {

        int arr[] = {1,2,2,3,4};
        System.out.println(getUpperBound(arr, 3));
    }

    private static int getUpperBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length -1;
        int ans = -1;

        while(low <= high){
            int mid = low + (high - low)/2;
            if(arr[mid] > target){
                ans = arr[mid];
                high= mid -1;
            }else{
                low = mid +1;
            }
        }
        return ans;
    }
}
