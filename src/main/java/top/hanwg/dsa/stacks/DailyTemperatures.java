package top.hanwg.dsa.stacks;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Stack;

// #739 medium
// Given an array of integers temperatures represents the daily temperatures,
// return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
// If there is no future day for which this is possible, keep answer[i] == 0 instead.
public class DailyTemperatures {

    public int[] dailyTemperatures_bruteForce(int[] temperatures) {

        int[] answer = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            // scan ahead to find the next day with the higher temp
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }

        return answer;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];

        // consider inputs: 75,71,69,72,76
        // for 75, the next higher temp is 76
        // 76 is also higher than all the values we've seen so far
        //      76 > 71
        //      76 > 69
        //      76 > 72
        // once we found 76, we can solve for all these values
        // hence, we need to track these values in a data structure until we reach 76
        Stack<Integer[]> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {

            while (!stack.isEmpty() && temperatures[i] > stack.peek()[1]) {
                answer[stack.peek()[0]] = i - stack.peek()[0];
                stack.pop();
            }

            stack.push(new Integer[]{i, temperatures[i]});
        }

        return answer;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();

        int[] answer = solution.dailyTemperatures(new int[] { 73,74,75,71,69,72,76,73 });
        Assertions.assertArrayEquals(new int[] { 1,1,4,2,1,1,0,0 }, answer);

        answer = solution.dailyTemperatures(new int[] { 30,40,50,60 });
        Assertions.assertArrayEquals(new int[] { 1,1,1,0 }, answer);

        answer = solution.dailyTemperatures(new int[] { 30,60,90 });
        Assertions.assertArrayEquals(new int[] { 1,1,0 }, answer);
    }
}
