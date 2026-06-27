package com.practice.BinarySerach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Confluent
space : O(n)
set: 0(1)
get: not 0(1).... but 0(log n) because we will use binary search.
Note. Here value is not just an String, it will have timesanp and string value.
 So get will not be on O(1).. bruteforce will have O(n) for get..
  that's wh we apply Binar Search.

  Problem: Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
  and retrieve the key's value at a certain timestamp.

  All the timestamps timestamp of set are strictly increasing.
   If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 */
public class TimeBasedKeyValueStore {

    static class Pair {

        int timeStamp;
        String val;

        Pair(int timeStamp, String val) {
            this.timeStamp = timeStamp;
            this.val = val;
        }
    }
    Map<String, List<Pair>> map;

    TimeBasedKeyValueStore() {
        this.map = new HashMap<>();
    }

    void set(String key, String val, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>())
                .add(new Pair(timestamp, val));
    }
    /*
    All timestamps for a key are strictly increasing.
    So we are considering list to be sorted. and able to apply Binary Serach.
    If thiwas not case, we needed to use TreeMap<Integer, Map> instead of List<Pair>
    where even set compexity will be O(log n).
     */
    String get(String k, int timeStamp){
        String result = "";
        if(!map.containsKey(k)){
            return "";
        }else{
            List<Pair> list = map.get(k);
            int low = 0;
            int high = list.size() -1;
            while(low <= high){
                int mid = low +(high -low)/2;
                /*
 If there are multiple such values,
 it returns the value associated with the largest timestamp_prev.
 ---------------highest value lower than target.-----------
  eg: timestamps = [1, 4, 7]
      target = 5
                 */
                if(timeStamp >= list.get(mid).timeStamp){
                    result =  list.get(mid).val;
                    low = mid+1;
                }
                else {
                    high = mid - 1;
                }

            }

        }
        return result;
    }

    public static void main(String[] args) {

        TimeBasedKeyValueStore timeMap = new TimeBasedKeyValueStore();

        timeMap.set("foo", "bar", 1);

        System.out.println(timeMap.get("foo", 1)); // bar
        System.out.println(timeMap.get("foo", 3)); // bar

        timeMap.set("foo", "bar2", 4);

        System.out.println(timeMap.get("foo", 4)); // bar2
        System.out.println(timeMap.get("foo", 5)); // bar2
    }

}
