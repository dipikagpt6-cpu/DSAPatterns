package com.practice.HashMap;

import java.util.HashMap;
import java.util.Map;

class TimeLimitedCache {

    private static class Entry {
        int value;
        long expiryTime;

        Entry(int value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }

    private final Map<Integer, Entry> map;

    public TimeLimitedCache() {
        map = new HashMap<>();
    }

    public boolean set(int key, int value, int duration) {

        long currentTime = System.currentTimeMillis();

        boolean exists =
                map.containsKey(key)
                        && map.get(key).expiryTime > currentTime;

        map.put(
                key,
                new Entry(
                        value,
                        currentTime + duration
                )
        );

        return exists;
    }

    public int get(int key) {

        long currentTime = System.currentTimeMillis();

        Entry entry = map.get(key);

        if (entry == null || entry.expiryTime <= currentTime) {
            return -1;
        }

        return entry.value;
    }
//O(n)
    public int count() {

        long currentTime = System.currentTimeMillis();

        int count = 0;

        for (Entry entry : map.values()) {
            if (entry.expiryTime > currentTime) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws InterruptedException {

        TimeLimitedCache cache = new TimeLimitedCache();

        System.out.println("----- SET -----");

        System.out.println(
                cache.set(1, 100, 3000)
        ); // false

        System.out.println(
                cache.set(2, 200, 5000)
        ); // false

        System.out.println();

        System.out.println("----- GET -----");

        System.out.println(
                cache.get(1)
        ); // 100

        System.out.println(
                cache.get(2)
        ); // 200

        System.out.println();

        System.out.println("----- COUNT -----");

        System.out.println(
                cache.count()
        ); // 2

        System.out.println();

        System.out.println("Waiting 4 seconds...");
        Thread.sleep(4000);

        System.out.println();

        System.out.println("----- AFTER 4 SECONDS -----");

        System.out.println(
                cache.get(1)
        ); // -1 (expired)

        System.out.println(
                cache.get(2)
        ); // 200

        System.out.println(
                cache.count()
        ); // 1

        System.out.println();

        System.out.println("----- UPDATE KEY 2 -----");

        System.out.println(
                cache.set(2, 999, 5000)
        ); // true

        System.out.println(
                cache.get(2)
        ); // 999

        System.out.println(
                cache.count()
        ); // 1

        System.out.println();

        System.out.println("Waiting 6 seconds...");
        Thread.sleep(6000);

        System.out.println();

        System.out.println("----- FINAL STATE -----");

        System.out.println(
                cache.get(1)
        ); // -1

        System.out.println(
                cache.get(2)
        ); // -1

        System.out.println(
                cache.count()
        ); // 0
    }
}