package com.practice.SlidingWindow;

import java.util.HashMap;

public class FruitsIntoBasket {

    static void main() {
        int[] fruits = {0,1,0,2,1};
        System.out.println(fruitsIntoBasket(fruits, 3));
    }

    private static Integer fruitsIntoBasket(int[] fruits, int k) {
        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        HashMap<Integer, Integer> map =  new HashMap<>();
        for(int end = 0 ; end < fruits.length; end++){
            map.put(fruits[end], map.getOrDefault(fruits[end] , 0)+1);
            while(map.size()>k){
                map.put(fruits[start], map.getOrDefault(fruits[start], 0)-1 );
                if(map.get(fruits[start]) == 0){
                    map.remove(fruits[start]);
                }
                start++;

            }
            maxLen = Math.max(maxLen, end - start +1);
        }
        return maxLen;
    }
}
