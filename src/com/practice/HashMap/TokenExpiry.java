package com.practice.HashMap;

import java.util.HashMap;
import java.util.Map;

public class TokenExpiry {

    int TTL;
    Map<String, Integer> expirymap;

    TokenExpiry(int ttl) {
        TTL = ttl;
        expirymap = new HashMap<>();
    }

    void generateToken(String token, int timeStamp) {
        expirymap.put(token, timeStamp + TTL);
    }

    void renewToken(String token, int timeStamp) {
        if (!expirymap.containsKey(token))
            return;
        int expirytime = expirymap.get(token);
        if (expirytime >= timeStamp) {
            expirymap.put(token, timeStamp + TTL);
        }
    }

    int countNoOFTokensExpired(int timestamp) {

        int count = 0;
        for (int expiry : expirymap.values()) {
            if (expiry > timestamp) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        TokenExpiry am =
                new TokenExpiry(5);

        am.generateToken("aaa", 2);
        am.renewToken("aaa", 6);

        System.out.println(
                am.countNoOFTokensExpired(6)); // 1

        am.generateToken("bbb", 7);

        System.out.println(
                am.countNoOFTokensExpired(8)); // 2

        System.out.println(am.countNoOFTokensExpired(15));

    }
}
