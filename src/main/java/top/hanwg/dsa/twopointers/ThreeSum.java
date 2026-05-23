package top.hanwg.dsa.twopointers;

import org.junit.jupiter.api.Assertions;

import java.util.*;

// #15 medium
// Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
// Notice that the solution set must not contain duplicate triplets.
public class ThreeSum {

    // brute force
    public List<List<Integer>> threeSum(int[] nums) {
        // set ensures no duplicates
        Set<List<Integer>> answer = new HashSet<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = List.of(nums[i], nums[j], nums[k]);
                        answer.add(list);
                    }
                }
            }
        }
        return answer.stream().toList();
    }

    public List<List<Integer>> threeSum_2pointers(int[] nums) {
        Set<List<Integer>> answer = new HashSet<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            twoSum(answer, nums, target, nums[i], i + 1);
        }

        return answer.stream().toList();
    }

    private void twoSum(Set<List<Integer>> answer, int[] nums, int target, int num, int start) {
        int end = nums.length - 1;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                answer.add(List.of(num, nums[start], nums[end]));
            }

            if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        Assertions.assertArrayEquals(new int[][]{{-1, -1, 2}, {-1, 0, 1}},
                solution.threeSum_2pointers(new int[]{-1, 0, 1, 2, -1, -4}).stream()
                        .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                        .toArray(int[][]::new));

        Assertions.assertArrayEquals(new int[][]{},
                solution.threeSum_2pointers(new int[]{0, 1, 1}).stream()
                        .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                        .toArray(int[][]::new));

        Assertions.assertArrayEquals(new int[][]{{0, 0, 0}},
                solution.threeSum_2pointers(new int[]{0, 0, 0}).stream()
                        .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                        .toArray(int[][]::new));
    }
}
