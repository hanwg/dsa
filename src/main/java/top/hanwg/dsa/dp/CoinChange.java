package top.hanwg.dsa.dp;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

// #322 medium
// You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.
// Return the fewest number of coins that you need to make up the exact target amount.
// If it is impossible to make up the amount, return -1.
// You may assume that you have an unlimited number of each coin.
public class CoinChange {

    // key: amount
    // value: min no. of coins
    Map<Integer, Integer> dp = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (dp.containsKey(amount)) {
            return dp.get(amount);
        }

        int minCoins = Integer.MAX_VALUE;

        // min(
        //   no. of coins for (amount - coins[0])
        //   no. of coins for (amount - coins[1])
        //   no. of coins for (amount - coins[n])
        // )

        for (int coin : coins) {
            int remainder = amount - coin;
            if (remainder >= 0) {
                int numCoins = coinChange(coins, remainder);
                if (numCoins != -1) {
                    minCoins = Math.min(minCoins, numCoins + 1);
                }
            }
        }

        if (minCoins == Integer.MAX_VALUE) {
            minCoins = -1;
        }

        dp.put(amount, minCoins);
        return minCoins;
    }

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        Assertions.assertEquals(0, solution.coinChange(new int[]{1}, 0));

        solution = new CoinChange();
        Assertions.assertEquals(-1, solution.coinChange(new int[]{2}, 3));

        solution = new CoinChange();
        Assertions.assertEquals(2, solution.coinChange(new int[]{2,3}, 6));

        solution = new CoinChange();
        Assertions.assertEquals(3, solution.coinChange(new int[]{1, 5, 10}, 12));
    }
}
