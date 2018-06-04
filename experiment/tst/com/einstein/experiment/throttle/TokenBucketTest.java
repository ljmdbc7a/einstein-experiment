package com.einstein.experiment.throttle;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TokenBucketTest {

    private TokenBucket tokenBucket;

    private RateLimiter rateLimiter;

    @Before
    public void setUp() {
        tokenBucket = new TokenBucket(1000, 450, 1, TimeUnit.SECONDS);
        rateLimiter = RateLimiter.create(450);
    }

    @Test
    public void testConsume() {
        Map<Long, Long> numsPerSecond = new HashMap<>();
        while (true) {
            tokenBucket.consume();
            rateLimiter.acquire();
            Long nowSecond = System.currentTimeMillis() / 1000;
            if (numsPerSecond.get(nowSecond) == null) {
                numsPerSecond.put(nowSecond, 1L);
            } else {
                numsPerSecond.put(nowSecond, numsPerSecond.get(nowSecond) + 1);
            }
            if (numsPerSecond.size() > 10) {
                System.out.println(numsPerSecond);
                break;
            }
        }
        Assert.assertTrue(numsPerSecond.values().stream().max(Long::compareTo).get() > 0);
    }
}
