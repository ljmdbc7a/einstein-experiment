package com.einstein.experiment.throttle;

import com.google.common.base.Ticker;
import lombok.ToString;

import java.util.concurrent.TimeUnit;

/**
 * A refilling strategy for a token bucket.
 */
@ToString(exclude = {"ticker", "lastRefillTime", "nextRefillTime"})
class RefillStrategy {

    private final Ticker ticker = Ticker.systemTicker();

    private final long numTokensPerPeriod;
    private final long periodDurationInNanos;

    private long lastRefillTime;
    private long nextRefillTime;

    /**
     * Create a RefillStrategy.
     *
     * @param numTokensPerPeriod The number of tokens to add to the bucket
     *                           every period.
     * @param period             How often to refill the bucket.
     * @param unit               Unit for period.
     */
    public RefillStrategy(long numTokensPerPeriod, long period, TimeUnit unit) {
        this.numTokensPerPeriod = numTokensPerPeriod;
        this.periodDurationInNanos = unit.toNanos(period);
        this.lastRefillTime = -periodDurationInNanos;
        this.nextRefillTime = -periodDurationInNanos;
    }

    /**
     * Returns the number of tokens to add to the token bucket.
     */
    public long refill() {
        long now = ticker.read();
        if (now < nextRefillTime) {
            return 0;
        }
        // We now know that we need to refill the bucket with some tokens, the question
        // is how many.  We need to count how many periods worth of tokens we've missed.
        long numPeriods = Math.max(0, (now - lastRefillTime) / periodDurationInNanos);

        lastRefillTime += numPeriods * periodDurationInNanos;
        nextRefillTime = lastRefillTime + periodDurationInNanos;

        return numPeriods * numTokensPerPeriod;
    }

    /**
     * Estimate how long until a token is ready. This is a best guess estimate
     * based on a single thread requesting tokens. A client may call this after
     * failing to get a token to figure out how long to sleep before trying again.
     *
     * @return Return the amount of time until the next group of tokens can be added
     * to the token bucket.
     */
    public long getDurationUntilNextRefill(TimeUnit unit) {
        long now = ticker.read();
        return unit.convert(Math.max(0, nextRefillTime - now), TimeUnit.NANOSECONDS);
    }
}
