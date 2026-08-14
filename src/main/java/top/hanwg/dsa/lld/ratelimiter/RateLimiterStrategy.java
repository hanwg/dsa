package top.hanwg.dsa.lld.ratelimiter;

public abstract class RateLimiterStrategy {
    int count;
    int timePeriod;

    public RateLimiterStrategy(int count, int timePeriod) {
        this.count = count;
        this.timePeriod = timePeriod;
    }

    public abstract boolean isAllowed(int timestamp);
}
