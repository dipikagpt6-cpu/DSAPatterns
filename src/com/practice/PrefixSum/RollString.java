package com.practice.PrefixSum;

import java.util.Arrays;

public class RollString {
    static void main() {

        String s ="bca";
        int[] arr = {1,3,3};
        System.out.println(getRolledString(arr,s));
    }

    private static String getRolledString(int[] arr, String s) {

        int[] freq = new int[arr.length];
        for(int i = 0 ; i< arr.length ; i++){
          freq[arr[i]-1]++;
        }
        for(int i = freq.length-2;i >=0 ; i--){
            freq[i] = freq[i] + freq[i+1];
        }

        char[] result = s.toCharArray();
        for(int i = 0 ; i< freq.length ; i++){
            int shift = freq[i] % 26;
            result[i] = (char) (((result[i] - 'a') + shift)%26 + 'a');
        }
        return new String(result);
    }
}
