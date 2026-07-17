package top.hanwg.dsa.arrays;

import org.junit.jupiter.api.Assertions;


// #238 medium
// Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
// You must write an algorithm that runs in O(n) time and without using the division operation.
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] results = new int[nums.length];
        int product = 1;

        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                product *= nums[i];
                results[i] = product;
            }
        }

        // if there are multiple 0's, the product is always 0
        if (zeroCount > 1) {
            return new int[nums.length];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (zeroCount > 0) {
                    results[i] = 0;
                } else {
                    results[i] = product / nums[i];
                }
            } else {
                results[i] = product;
            }
        }

        return results;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();

        Assertions.assertArrayEquals(new int[]{0, 1}, solution.productExceptSelf(new int[]{1, 0}));

        Assertions.assertArrayEquals(new int[]{0, 0, 0}, solution.productExceptSelf(new int[]{0, 4, 0}));

        Assertions.assertArrayEquals(new int[]{24, 0, 0, 0}, solution.productExceptSelf(new int[]{0, 2, 3, 4}));

        Assertions.assertArrayEquals(new int[]{0, 2, 0}, solution.productExceptSelf(new int[]{1, 0, 2}));

        Assertions.assertArrayEquals(new int[]{24, 12, 8, 6}, solution.productExceptSelf(new int[]{1, 2, 3, 4}));
    }
}
