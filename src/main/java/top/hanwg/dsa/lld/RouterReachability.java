package top.hanwg.dsa.lld;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// https://codezym.com/question/149-router-reachability-broadcast-shutdown-message
public class RouterReachability {

    public record Router(String routerId, int x, int y, String status) { }

    boolean canReachDestination(List<String> routers, int range, String source, String destination) {
        Map<String, Router> routerMap = routers.stream()
                .map(string -> {
                    String[] tokens = string.split(",");
                    return new Router(tokens[0],
                            Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            tokens[3]);
                })
                .collect(Collectors.toMap(Router::routerId, Function.identity()));

        Set<String> badRouters = new HashSet<>();

        return search(badRouters, routerMap, range, source, destination);
    }

    private boolean search(Set<String> badRouters, Map<String, Router> routers, int range, String source, String destination) {

        if (Objects.equals(source, destination)) {
            return true;
        }

        badRouters.add(source);

        Router sourceRouter = routers.get(source);
        for (Router router : routers.values()) {
            if (badRouters.contains(router.routerId)) {
                continue;
            }

            double distance = distance(sourceRouter, router);
            if (distance <= 10) {
                boolean found = search(badRouters, routers, range, router.routerId, destination);
                if (found) {
                    return true;
                }

                badRouters.remove(router.routerId);
            }
        }

        return false;
    }

    double distance(Router r1, Router r2) {
        int dx = r1.x - r2.x;
        int dy = r1.y - r2.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static void main(String[] args) {
        RouterReachability solution = new RouterReachability();
        System.out.println(solution.canReachDestination(List.of("A,0,0,WORKING", "B,0,8,WORKING", "C,0,17,WORKING", "D,11,0,WORKING"), 10, "A", "D"));
        System.out.println(solution.canReachDestination(List.of("A,0,0,WORKING", "B,3,4,WORKING", "C,6,8,WORKING"), 5, "A", "C"));
    }
}
