package com.practice.queue.prioritQueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.PriorityQueue;

public class TimeLimitedCache {

    static class CacheEntry {
        int value;
        long expiryTime;

        CacheEntry(int value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }

    static class ExpiryRecord {
        int key;
        long expiryTime;

        ExpiryRecord(int key, long expiryTime) {
            this.key = key;
            this.expiryTime = expiryTime;
        }
    }

    private final ConcurrentHashMap<Integer, CacheEntry> cache =
            new ConcurrentHashMap<>();

    private final PriorityQueue<ExpiryRecord> expiryQueue =
            new PriorityQueue<>(
                    (a, b) ->
                            Long.compare(
                                    a.expiryTime,
                                    b.expiryTime));

    private final ReentrantLock lock =
            new ReentrantLock();

    private void cleanup() {

        long currentTime =
                System.currentTimeMillis();

        lock.lock();

        try {

            while (!expiryQueue.isEmpty()
                    && expiryQueue.peek().expiryTime
                    <= currentTime) {

                ExpiryRecord record =
                        expiryQueue.poll();

                CacheEntry currentEntry =
                        cache.get(record.key);

                /*
                 * Lazy deletion:
                 * remove only if this is still
                 * the latest expiry.
                 */
                if (currentEntry != null
                        && currentEntry.expiryTime
                        == record.expiryTime) {

                    cache.remove(record.key);
                }
            }

        } finally {
            lock.unlock();
        }
    }

    public boolean set(
            int key,
            int value,
            int durationMillis) {

        cleanup();

        long expiryTime =
                System.currentTimeMillis()
                        + durationMillis;

        CacheEntry existing =
                cache.get(key);

        boolean existed =
                existing != null
                        && existing.expiryTime >
                        System.currentTimeMillis();

        cache.put(
                key,
                new CacheEntry(
                        value,
                        expiryTime));

        lock.lock();

        try {

            expiryQueue.offer(
                    new ExpiryRecord(
                            key,
                            expiryTime));

        } finally {
            lock.unlock();
        }

        return existed;
    }

    public int get(int key) {

        cleanup();

        CacheEntry entry =
                cache.get(key);

        if (entry == null) {
            return -1;
        }

        return entry.value;
    }

    public int count() {

        cleanup();

        return cache.size();
    }

    public static void main(String[] args)
            throws Exception {

        TimeLimitedCache cache =
                new TimeLimitedCache();

        System.out.println(
                cache.set(1, 100, 3000));
        // false

        System.out.println(
                cache.get(1));
        // 100

        System.out.println(
                cache.count());
        // 1

        Thread.sleep(4000);

        System.out.println(
                cache.get(1));
        // -1

        System.out.println(
                cache.count());
        // 0
    }
}