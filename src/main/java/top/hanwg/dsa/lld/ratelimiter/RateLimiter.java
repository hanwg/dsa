package top.hanwg.dsa.lld.ratelimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    Map<String, RateLimiterStrategy> rateLimits = new HashMap<>();

    public void addResource(String resourceId, String strategy, String limits) {
        if (!limits.matches("[0-9]+,[0-9]+")) {
            throw new IllegalArgumentException("Invalid limit");
        }

        String[] tokens = limits.split(",");
        int count = Integer.parseInt(tokens[0]);
        int timePeriod = Integer.parseInt(tokens[1]);

        RateLimiterStrategy rateLimitStrategy;
        switch (strategy) {
            case "fixed-window-counter":
                rateLimitStrategy = new FixedWindowStrategy(count, timePeriod);
                rateLimits.put(resourceId, rateLimitStrategy);
                return;
            case "sliding-window-counter":
                rateLimitStrategy = new SlidingWindowStrategy(count, timePeriod);
                rateLimits.put(resourceId, rateLimitStrategy);
                return;
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);
        }
    }

    public boolean isAllowed(String resourceId, int timestamp) {
        RateLimiterStrategy strategy = rateLimits.get(resourceId);
        return strategy.isAllowed(timestamp);
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();
        rateLimiter.addResource("login-api", "fixed-window-counter", "2,5");

        System.out.println(rateLimiter.isAllowed("login-api", 1));
        System.out.println(rateLimiter.isAllowed("login-api", 2));
        System.out.println(rateLimiter.isAllowed("login-api", 4));
        System.out.println(rateLimiter.isAllowed("login-api", 5));

        rateLimiter.addResource("login-api2", "sliding-window-counter", "2,5");
        System.out.println(rateLimiter.isAllowed("login-api2", 13));
        System.out.println(rateLimiter.isAllowed("login-api2", 14));
        System.out.println(rateLimiter.isAllowed("login-api2", 15));
        System.out.println(rateLimiter.isAllowed("login-api2", 17));
        System.out.println(rateLimiter.isAllowed("login-api2", 18));
    }
}
