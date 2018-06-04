package com.einstein.experiment.throttle;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.ToString;

import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;

@ToString(exclude = {"currentTokens"})
public class TokenBucket {

    private final long capacity;
    private final RefillStrategy refillStrategy;

    private long currentTokens;

    /**
     * @param capacity       bucket capacity means max currentTokens in bucket
     * @param refillStrategy Refilling strategy for a token bucket.
     */
    private TokenBucket(long capacity, RefillStrategy refillStrategy) {
        this.capacity = capacity;
        this.refillStrategy = refillStrategy;
    }

    /**
     * Create a TokenBucket instance.
     */
    public TokenBucket(long capacity, long numTokensPerPeriod, long period, TimeUnit unit) {
        this(capacity, new RefillStrategy(numTokensPerPeriod, period, unit));
    }

    /**
     * Attempt to consume a single token from the bucket.  If it was consumed then
     * {@code true} is returned, otherwise {@code false} is returned.
     *
     * @return {@code true} if a token was consumed, {@code false} otherwise.
     */
    public boolean tryConsume() {
        return tryConsume(1);
    }

    public synchronized boolean tryConsume(long numTokens) {
        checkArgument(numTokens > 0,
                "Number of tokens to consume must be positive");
        checkArgument(numTokens <= capacity,
                "Number of tokens to consume must be less than the capacity of the bucket.");

        refill(refillStrategy.refill());

        // Now try to consume some tokens
        if (numTokens <= currentTokens) {
            currentTokens -= numTokens;
            return true;
        }

        return false;
    }

    /**
     * Consume a single token from the bucket. If no token is currently available
     * then this method will block until a token becomes available.
     */
    public void consume() {
        consume(1);
    }

    public void consume(long numTokens) {
        while (true) {
            if (tryConsume(numTokens)) {
                break;
            }
            // Sleep until the next group of tokens can be added
            Uninterruptibles.sleepUninterruptibly(
                    refillStrategy.getDurationUntilNextRefill(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
        }
    }

    /**
     * Refills the bucket with the specified number of tokens. If the bucket is currently
     * full or near capacity then fewer than {@code numTokens} may be added.
     *
     * @param numTokens The number of tokens to add to the bucket.
     */
    private void refill(long numTokens) {
        long newTokens = Math.min(capacity, Math.max(0, numTokens));
        currentTokens = Math.max(0, Math.min(currentTokens + newTokens, capacity));
    }
}
