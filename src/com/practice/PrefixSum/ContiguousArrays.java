package com.practice.PrefixSum;

import java.util.HashMap;

public class ContiguousArrays {
    //length of longest subarray where equal no's of zeros and 1's
    //covert 0 to -1
    //when sum = 0, thats the ans
    //save index as value;

    static void main() {
        int[] arr= {0,1,1,1,0,0,0};
        System.out.println(getLength(arr));
    }

    private static int getLength(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        int maxLen = 0;
        for(int i =0 ; i < arr.length; i++){
            if(arr[i] == 0)
                sum += -1;
            else
                sum += 1;

            if(map.containsKey(sum)){
                maxLen = Math.max(maxLen, i - map.get(sum));
            }else{
                map.put(sum,i);
            }

        }
return maxLen;
    }


}
