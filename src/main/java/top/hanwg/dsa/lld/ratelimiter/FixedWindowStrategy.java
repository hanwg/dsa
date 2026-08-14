package top.hanwg.dsa.lld.ratelimiter;

public class FixedWindowStrategy extends RateLimiterStrategy {

    int currentWindow = 0;
    int currentCount = 0;

    public FixedWindowStrategy(int count, int timePeriod) {
        super(count, timePeriod);
    }

    @Override
    public boolean isAllowed(int timestamp) {
        // determine the window
        int window = timestamp / timePeriod;
        if (window > currentWindow) {
            currentWindow = window;
            currentCount = 0;
        }

        if (currentCount < count) {
            currentCount++;
            return true;
        }

        return false;
    }
}
