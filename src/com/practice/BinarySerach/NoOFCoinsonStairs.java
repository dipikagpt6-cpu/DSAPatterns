package com.practice.BinarySerach;

public class NoOFCoinsonStairs {

    static void main() {
        System.out.println(getNoOfStairsComletelyFilledwithcoins(5));
    }

    private static int getNoOfStairsComletelyFilledwithcoins(int n) {

        /*we need to use formula K(K+1)/2. This says no. of coins needed
        for any no. of stairs for example:
        for 3 rows no. of coins needed = 1+2+3 = 6 = 3*(3+1)/2
         */

        int left = 1;
        int right =n;
        while(left < right){
            int mid = left+(right-left)/2;
            int noOfCoinsNeeded = mid *(mid+1)/2;
            if(noOfCoinsNeeded == n){
                return mid;
            }else if(noOfCoinsNeeded > n){
                right = mid -1;

            }else{
                left = mid +1;
            }
        }
        return right;
    }
}
