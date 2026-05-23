package top.hanwg.dsa.hash;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// #1 easy
// Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
// You can return the answer in any order.
// Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
// You can return the answer in any order.
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        // key = num
        // value = index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }

            map.put(nums[i], i);
        }

        return null;
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        int[] answer = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        Arrays.sort(answer);
        Assertions.assertArrayEquals(new int[]{0, 1}, answer);

        answer = solution.twoSum(new int[]{3, 2, 4}, 6);
        Arrays.sort(answer);
        Assertions.assertArrayEquals(new int[]{1, 2}, answer);

        answer = solution.twoSum(new int[]{3, 3}, 6);
        Arrays.sort(answer);
        Assertions.assertArrayEquals(new int[]{0, 1}, answer);
    }
}
