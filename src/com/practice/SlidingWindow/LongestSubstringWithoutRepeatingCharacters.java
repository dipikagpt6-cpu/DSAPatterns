package com.practice.SlidingWindow;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {

    static void main() {

        String s = "abcdbghdey";
        System.out.println(getLongestSubstring(s));

    }

    private static Integer getLongestSubstring(String s) {

        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        HashMap<Character, Integer> map =  new HashMap<>();
        for(int end = 0; end < s.length(); end++){
            if(map.containsKey(s.charAt(end))){
                Character leftChar = s.charAt(start);
                map.remove(leftChar);
                start++;
            }

            map.put(s.charAt(end), 1);
            maxLen = Math.max(maxLen, end - start +1);


        }
        return maxLen;

    }
}
