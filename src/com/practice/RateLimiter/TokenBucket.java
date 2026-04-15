package com.practice.RateLimiter;

public class TokenBucket {

    private final long capacity;
    private final long refillRatePerSecond;

    private double currentTokens;
    private long lastRefillTimestamp;

    public TokenBucket(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.currentTokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
    }

    // Try to consume 1 token
    public synchronized boolean allowRequest() {
        refill();

        if (currentTokens >= 1) {
            currentTokens -= 1;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillTimestamp) / 1_000_000_000.0;

        double tokensToAdd = elapsedSeconds * refillRatePerSecond;

        if (tokensToAdd > 0) {
            currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }

    // MAIN METHOD
    public static void main(String[] args) throws InterruptedException {
        TokenBucket bucket = new TokenBucket(5, 2); // capacity=5, refill=2 tokens/sec

        for (int i = 1; i <= 15; i++) {
            boolean allowed = bucket.allowRequest();

            System.out.println("Request " + i + " -> " + (allowed ? "ALLOWED" : "REJECTED"));

            Thread.sleep(300); // simulate incoming traffic
        }
    }
}