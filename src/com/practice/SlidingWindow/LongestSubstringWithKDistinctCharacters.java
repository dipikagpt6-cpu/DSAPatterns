package com.practice.SlidingWindow;

import java.util.HashMap;

public class LongestSubstringWithKDistinctCharacters {

    static void main() {
        String s = "adcfegfhggfeehfegh";
        System.out.println(getLongestSubstring(s, 3));
    }

    private static Integer getLongestSubstring(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = Integer.MIN_VALUE;
        int start = 0;

        for(int end = 0; end < s.length(); end ++){
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0)+1);
            while(map.size() > k){
                Character leftChar = s.charAt(start);
                map.put(leftChar, map.getOrDefault(leftChar,0)-1);
                if(map.get(leftChar) == 0){
                    map.remove(leftChar);
                }
                start ++;

            }
            maxLen = Math.max(maxLen, end - start +1);
        }
        return maxLen;

    }


}
