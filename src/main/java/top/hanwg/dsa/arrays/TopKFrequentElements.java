package top.hanwg.dsa.arrays;

import org.junit.jupiter.api.Assertions;

import java.util.*;

// #347 medium
// Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
public class TopKFrequentElements {

    public int[] maxHeap(int[] nums, int k) {

        // count occurrences for each element
        // key - element
        // value - no. of occurrences
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int occurrences = map.getOrDefault(nums[i], 0);
            map.put(nums[i], occurrences + 1);
        }

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();

        // min-heap
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((entry1, entry2) -> {
            return entry2.getValue().compareTo(entry1.getValue());
        });

        map.entrySet()
                .stream()
                .forEach(entry -> {
                    maxHeap.add(entry);
                });

        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = maxHeap.remove();
            answer[i] = entry.getKey();
        }

        return answer;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        int[] answer = solution.maxHeap(new int[] { 1,1,1,2,2,3 }, 2);
        Arrays.sort(answer);
        Assertions.assertArrayEquals(new int[] { 1, 2 }, answer);

        answer = solution.maxHeap(new int[] { 1 }, 1);
        Assertions.assertArrayEquals(new int[] { 1 }, answer);
    }
}
