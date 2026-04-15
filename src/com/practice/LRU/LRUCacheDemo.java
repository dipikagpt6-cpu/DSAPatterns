package com.practice.LRU;

public class LRUCacheDemo {

    public static void main(String[] args) {
        MyLRUCache<Integer, String> cache = new MyLRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println(cache.get(1)); // A (1 becomes most recent)

        cache.put(4, "D"); // Evicts key 2 (least recently used)

        System.out.println(cache.get(2)); // null (evicted)
        System.out.println(cache.get(3)); // C
        System.out.println(cache.get(4)); // D

        cache.put(5, "E"); // Evicts key 1

        System.out.println(cache.get(1)); // null
        System.out.println(cache.get(5)); // E
    }
}