package com.practice.BinarySerach;

public class SerchInInfiniteSortedArray {

    static void main() {
        int arr[] = {1,2,3,5,7,9,11,15,18,21,25};
        System.out.println(giveIndexOFTarget(arr, 15));
    }

    private static int giveIndexOFTarget(int[] arr, int target) {

        int low = 0;
        int high = 1;
        while(arr[high] < target){
            int newlow = high +1;
            high = high +((high - low)+1)*2;
            low = newlow;
            if(high >= arr.length) {
                high = arr.length - 1;
                break;
            }
        }
        return binarySearch(arr, target, low, high);
    }

    static int binarySearch(int[] arr, int target, int low, int high) {

        while(low <= high) {

            int mid = low + (high - low) / 2;

            if(arr[mid] == target) {
                return mid;
            }

            else if(arr[mid] < target) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
