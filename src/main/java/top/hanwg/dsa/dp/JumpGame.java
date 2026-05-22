package top.hanwg.dsa.dp;

import org.junit.jupiter.api.Assertions;

// #55 medium
// You are given an integer array nums.
// You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
// Return true if you can reach the last index, or false otherwise.
public class JumpGame {

    public boolean canJump(int[] nums) {

        // tracks what has been solved
        boolean[] dp = new boolean[nums.length];

        return canJump(dp, nums, 0);
    }

    private boolean canJump(boolean[] dp, int[] nums, int start) {

        if (start + nums[start] >= nums.length - 1)
            return true;

        for (int i = 1; i <= nums[start]; i++) {
            if (!dp[start + i] && canJump(dp, nums, start + i)) {
                return true;
            }
        }
        dp[start] = true;
        return false;
    }

    public static void main(String[] args) {
        JumpGame solution = new JumpGame();

        Assertions.assertTrue(solution.canJump(new int[]{2, 3, 1, 1, 4}));

        Assertions.assertFalse(solution.canJump(new int[]{3, 2, 1, 0, 4}));

        Assertions.assertTrue(solution.canJump(new int[]{1,1,1,0}));

        Assertions.assertTrue(solution.canJump(new int[]{1,2,0,1}));

        Assertions.assertTrue(solution.canJump(new int[]{3,0,8,2,0,0,1}));
    }
}
