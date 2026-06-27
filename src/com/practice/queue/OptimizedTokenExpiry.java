package com.practice.queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class OptimizedTokenExpiry {

    static class Token {
        String tokenId;
        int expiryTime;

        Token(String tokenId, int expiryTime) {
            this.tokenId = tokenId;
            this.expiryTime = expiryTime;
        }
    }
    private final int timeToLive;
    private final Map<String, Integer> tokenMap;
    private final Queue<Token> queue;

    OptimizedTokenExpiry(int timeToLive) {
        this.timeToLive = timeToLive;
        this.tokenMap = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    void cleanup(int currentTimestamp) {

        while (!queue.isEmpty() && queue.peek().expiryTime <= currentTimestamp) {
            Token token = queue.poll();
            Integer expiryTime = tokenMap.get(token.tokenId);
            //this check is necessar as same tken can be there in queue while renew.
            if (expiryTime != null && expiryTime == token.expiryTime) {
                tokenMap.remove(token.tokenId);
            }
        }
    }

            public void generate(String tokenId, int currentTime) {

                cleanup(currentTime);

                int expiry = currentTime + timeToLive;

                tokenMap.put(tokenId, expiry);
                queue.offer(new Token(tokenId, expiry));
            }

            public void renew(String tokenId, int currentTime) {

                cleanup(currentTime);

                Integer expiry = tokenMap.get(tokenId);

                if (expiry == null) {
                    return;
                }

                int newExpiry = currentTime + timeToLive;

                tokenMap.put(tokenId, newExpiry);
                queue.offer(new Token(tokenId, newExpiry));
            }

            public int countUnexpiredTokens(int currentTime) {

                cleanup(currentTime);

                return tokenMap.size();
            }

            public static void main(String[] args) {

                OptimizedTokenExpiry am =
                        new OptimizedTokenExpiry(5);

                am.generate("aaa", 2);
                am.renew("aaa", 6);

                System.out.println(
                        am.countUnexpiredTokens(6)); // 1

                am.generate("bbb", 7);

                System.out.println(
                        am.countUnexpiredTokens(8)); // 2

                System.out.println(
                        am.countUnexpiredTokens(15)); // 0
            }
        }



