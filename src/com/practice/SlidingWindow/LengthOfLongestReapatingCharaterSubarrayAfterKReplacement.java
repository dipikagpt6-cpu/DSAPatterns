package com.practice.SlidingWindow;


import java.util.HashMap;

public class LengthOfLongestReapatingCharaterSubarrayAfterKReplacement {

    static void main() {
        String str = "AABABBA";
        int length = findLongest(str, 1);
        System.out.println(length);
    }

    private static int findLongest(String str, int k) {

        int length = Integer.MIN_VALUE;
        int start = 0;
        int maxFreq = 0;
        int currentLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int end = 0 ; end < str.length(); end++){
            map.put(str.charAt(end), map.getOrDefault(str.charAt(end), 0)+1);
            maxFreq = Math.max(maxFreq, map.get(str.charAt(end)));
            currentLen = end-start+1;
            if(currentLen - maxFreq > k){
                map.put(str.charAt(start), map.get(str.charAt(start) )- 1);
                start++;
            }
            length = Math.max(length, end-start+1);

        }
        return length;
    }


}


