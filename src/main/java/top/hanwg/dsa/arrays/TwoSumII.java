package top.hanwg.dsa.arrays;

import org.junit.jupiter.api.Assertions;

// #167 medium
// Given an array of integers numbers that is sorted in non-decreasing order.
// Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number target and index1 < index2.
// Note that index1 and index2 cannot be equal, therefore you may not use the same element twice.
// There will always be exactly one valid solution.
//
// Your solution must use O(1) additional space.
public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            int search = target - numbers[i];

            // bsearch
            int left = i + 1;
            int right = numbers.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] == search) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] < search) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TwoSumII solution = new TwoSumII();

        Assertions.assertArrayEquals(new int[]{1, 3}, solution.twoSum(new int[]{2, 3, 4}, 6));

        Assertions.assertArrayEquals(new int[]{1, 2}, solution.twoSum(new int[]{1, 2, 3, 4}, 3));
    }
}
