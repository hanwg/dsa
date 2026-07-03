package top.hanwg.dsa.search;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

// #417 medium
// There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
// The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
// The island is partitioned into a grid of square cells.
// You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
// The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
// Water can flow from any cell adjacent to an ocean into the ocean.
// Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> solution = new ArrayList<>();

        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];

        // start from the oceans and explore inwards

        // top
        for (int i = 0; i < heights[0].length ; i++) {
            flows(heights, 0, i, pacific);
        }

        // right
        for (int i = 0; i < heights.length ; i++) {
            flows(heights, i, heights[0].length - 1, atlantic);
        }

        // bottom
        for (int i = 0; i < heights[0].length ; i++) {
            flows(heights, heights.length - 1, i, atlantic);
        }

        // left
        for (int i = 0; i < heights.length ; i++) {
            flows(heights, i, 0, pacific);
        }

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    solution.add(List.of(i, j));
                }
            }
        }

        return solution;
    }

    private void flows(int[][] heights, int row, int col, boolean[][] visited) {

        if (visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        // up
        if (row - 1 >= 0 && heights[row][col] <= heights[row - 1][col]) {
            flows(heights, row - 1, col, visited);
        }

        // right
        if (col + 1 < heights[row].length && heights[row][col] <= heights[row][col + 1]) {
            flows(heights, row, col + 1, visited);
        }

        // down
        if (row + 1 < heights.length && heights[row][col] <= heights[row + 1][col]) {
            flows(heights, row + 1, col, visited);
        }

        // left
        if (col - 1 >= 0 && heights[row][col] <= heights[row][col - 1]) {
            flows(heights, row, col - 1, visited);
        }
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();

        Assertions.assertEquals(
                List.of(List.of(0, 4), List.of(1, 3), List.of(1, 4), List.of(2, 2), List.of(3, 0), List.of(3, 1), List.of(4, 0)),
                solution.pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}}));

        Assertions.assertEquals(
                List.of(List.of(0, 0)),
                solution.pacificAtlantic(new int[][]{{1}}));

        Assertions.assertEquals(
                List.of(List.of(0, 0), List.of(1, 0)),
                solution.pacificAtlantic(new int[][]{{1}, {1}}));
    }
}
