package top.hanwg.dsa.backtracking;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// #39 medium
// Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
//The same number may be chosen from candidates an unlimited number of times.
// Two combinations are unique if the frequency of at least one of the chosen numbers is different.
//The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
public class CombinationSum {

    List<List<Integer>> answers = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        search(candidates, target, 0, new ArrayList<>());
        return answers;
    }

    private void search(int[] candidates, int remainder, int start, List<Integer> currentList) {
        if (remainder == 0) {
            answers.add(new ArrayList<>(currentList));
            return;
        }

        if (remainder < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            currentList.add(candidates[i]);
            search(candidates, remainder - candidates[i], i, currentList);
            currentList.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        List<List<Integer>> actual = solution.combinationSum(new int[]{2, 3, 6, 7}, 7);
        List<List<Integer>> expected = List.of(List.of(2, 2, 3), List.of(7));
        Assertions.assertEquals(expected, actual);
    }
}
