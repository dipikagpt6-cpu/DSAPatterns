package com.practice.SlidingWindow;

import java.util.HashMap;

public class MinSubstringWindow {

    static void main() {
        String s = "ACWDEQBHADCBEDW";
        String  t = "AW";
        System.out.println(minWindoSubstring(s, t));

    }

    private static String minWindoSubstring(String s, String t) {
       HashMap<Character, Integer> required = new HashMap<>();
       for(int  i = 0 ; i < t.length(); i++){
           required.put(t.charAt(i), required.getOrDefault(t.charAt(i), 0)+1);
       }
       int start =0;
       int minStart  =0;
       int minlen = Integer.MAX_VALUE;
       int count = 0;
       for(int end = 0 ; end < s.length(); end++) {
           Character endCh = s.charAt(end);
           if (required.containsKey(endCh)) {
               required.put(endCh, required.get(endCh) - 1);
               if (required.get(endCh) >= 0) {
                   count++;
               }
           }
           while (count == t.length()) {
               if (end - start + 1 < minlen) {
                   minlen = end - start + 1;
                   minStart = start;
               }
               Character leftChar = s.charAt(start);
               if (required.containsKey(leftChar)) {
                   required.put(leftChar, required.get(leftChar) + 1);
                   if (required.get(leftChar) > 0)
                       count--;
               }
               start++;
           }
       }
    return minlen == Integer.MAX_VALUE ? "": s.substring(minStart, minStart+minlen);

    }
}
