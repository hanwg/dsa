package top.hanwg.dsa.search;

import org.junit.jupiter.api.Assertions;

import java.util.*;

// #743 medium
// You are given a network of n directed nodes, labeled from 1 to n.
// You are also given times, a list of directed edges where times[i] = (ui, vi, ti).
// ui is the source node (an integer from 1 to n)
// vi is the target node (an integer from 1 to n)
// ti is the time it takes for a signal to travel from the source to the target node (an integer greater than or equal to 0).
// You are also given an integer k, representing the node that we will send a signal from.
// Return the minimum time it takes for all of the n nodes to receive the signal. If it is impossible for all the nodes to receive the signal, return -1 instead.
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        // adjacency list
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            List<int[]> adjNodes = adj.computeIfAbsent(source, x -> new ArrayList<>());
            adjNodes.add(new int[]{time[1], time[2]});
        }

        // distance of each node to reach k
        Map<Integer, Integer> distances = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }

        search(adj, k, 0, distances);

        // find min distance
        int minDistance = Collections.max(distances.values());
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }

        return minDistance;
    }

    public void search(Map<Integer, List<int[]>> adj, int k, int distanceTravelled, Map<Integer, Integer> distances) {
        int minDistance = distances.get(k);
        if (distanceTravelled < minDistance) {
            distances.put(k, distanceTravelled);
        } else {
            return;
        }

        if (!adj.containsKey(k)) {
            return;
        }

        List<int[]> adjNodes = adj.get(k);
        for (int[] nodes : adjNodes) {
            int target = nodes[0];
            int distance = distanceTravelled + nodes[1];
            search(adj, target, distance, distances);
        }
    }

    public static void main(String[] args) {
        NetworkDelayTime solution = new NetworkDelayTime();

        Assertions.assertEquals(3, solution.networkDelayTime(new int[][]{{1,2,1},{2,3,1},{1,4,4},{3,4,1}}, 4, 1));

        Assertions.assertEquals(-1, solution.networkDelayTime(new int[][]{{1,2,1},{2,3,1}}, 3, 2));
    }
}
