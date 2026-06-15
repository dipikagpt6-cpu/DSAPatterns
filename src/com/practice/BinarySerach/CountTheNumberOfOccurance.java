package com.practice.BinarySerach;

public class FirtsAndLastOccurance {
    static void main() {
        int arr[] = {1,5,7,7,7,8,8,8,10};
        System.out.println(getFirstOccurance(arr, 8));
        System.out.println(getLastOccurance(arr,8));

    }

    private static int getLastOccurance(int[] arr, int target) {

        int low = 0;
        int high = arr.length -1;
        int ans = -1;
        while(low <=high){
            int mid = low +(high -low)/2;
            if(arr[mid] == target){
                ans = mid;
                low = mid +1;
            }else if(arr[mid] < target){

                low = mid + 1;

            }
            else{
                high = mid -1;
            }
        }
return ans;

    }

    private static int getFirstOccurance(int[] arr, int target) {
        int low = 0;
        int high = arr.length -1;
        int ans = -1;
        while(low <=high){
            int mid = low +(high -low)/2;
            if(arr[mid] == target){
                ans = mid;
                high = mid -1;
            }else if(arr[mid] < target){

                low = mid + 1;

            }
            else{
                low = mid +1;
            }
        }
        return ans;


    }
}
