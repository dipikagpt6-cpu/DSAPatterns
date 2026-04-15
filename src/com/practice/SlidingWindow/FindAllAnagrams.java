package com.practice.SlidingWindow;

import java.util.*;

public class FindAllAnagrams {

    public static List<Integer> findAnagrams(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern.length() > text.length()) return result;

        HashMap<Character, Integer> map = new HashMap<>();
//pattern freqmap
        for (char c : pattern.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int windowStart = 0, matched = 0;

        for (int windowEnd = 0; windowEnd < text.length(); windowEnd++) {
            char rightChar = text.charAt(windowEnd);

            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) == 0) matched++;
            }

            if (windowEnd >= pattern.length() - 1) {

                if (matched == map.size()) {
                    result.add(windowStart);
                }

                char leftChar = text.charAt(windowStart);
                windowStart++;

                if (map.containsKey(leftChar)) {
                    if (map.get(leftChar) == 0) matched--;
                    map.put(leftChar, map.get(leftChar) + 1);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));

    }
}