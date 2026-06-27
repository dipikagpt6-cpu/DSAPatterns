package com.practice.queue.delayedQueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TimeLimitedCache {
    /*
    Write a class that allows getting and setting key-value pairs,
     however a time until expiration is associated with each key.

     A Delayed Queue (or Delay Queue) is a queue where elements become available
     for consumption only after a specified delay/expiry time.
     used: Authentication token expiry (LeetCode 1797)
Scheduled tasks
Cache expiration
Message retry after some delay
Rate limiting
     */
    private static class CacheEntry {
        int value;
        long expiryTime;

        CacheEntry(int value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }

    private static class ExpiringKey implements Delayed {

        int key;
        long expiryTime;

        ExpiringKey(int key, long expiryTime) {
            this.key = key;
            this.expiryTime = expiryTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {

            long remainingTime =
                    expiryTime - System.currentTimeMillis();

            return unit.convert(
                    remainingTime,
                    TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed other) {

            ExpiringKey otherKey =
                    (ExpiringKey) other;

            return Long.compare(
                    this.expiryTime,
                    otherKey.expiryTime);
        }
    }

    private final ConcurrentHashMap<Integer, CacheEntry> cache =
            new ConcurrentHashMap<>();

    private final DelayQueue<ExpiringKey> delayQueue =
            new DelayQueue<>();

    public TimeLimitedCache() {
        startCleanupThread();
    }

    private void startCleanupThread() {

        Thread cleanupThread = new Thread(() -> {

            while (true) {

                try {

                    // blocks until an item expires
                    ExpiringKey expiredKey =
                            delayQueue.take();

                    CacheEntry currentEntry =
                            cache.get(expiredKey.key);

                    /*
                     * Remove only if this expiry record
                     * is still the latest one.
                     */
                    if (currentEntry != null
                            && currentEntry.expiryTime
                            == expiredKey.expiryTime) {

                        cache.remove(expiredKey.key);
                    }

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        cleanupThread.setDaemon(true);
        cleanupThread.start();
    }

    public boolean set(
            int key,
            int value,
            int duration) {

        long expiryTime =
                System.currentTimeMillis()
                        + duration;

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

        delayQueue.offer(
                new ExpiringKey(
                        key,
                        expiryTime));

        return existed;
    }

    public int get(int key) {

        CacheEntry entry =
                cache.get(key);

        if (entry == null) {
            return -1;
        }

        if (entry.expiryTime
                <= System.currentTimeMillis()) {

            cache.remove(key, entry);

            return -1;
        }

        return entry.value;
    }

    public int count() {
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
