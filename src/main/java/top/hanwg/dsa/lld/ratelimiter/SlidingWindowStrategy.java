package top.hanwg.dsa.lld.ratelimiter;

public class SlidingWindowStrategy extends RateLimiterStrategy {

    int windowStart = 0;
    int windowEnd = 0;
    int currentCount = 0;

    public SlidingWindowStrategy(int count, int timePeriod) {
        super(count, timePeriod);

    }

    @Override
    public boolean isAllowed(int timestamp) {
        if (timestamp > windowEnd) {
            windowStart = timestamp;
            windowEnd = windowStart + timePeriod - 1;
            currentCount = 0;
        }

        if (timestamp <= windowEnd) {
            if (currentCount < count) {
                currentCount++;
                return true;
            }
        }
        return false;
    }
}
