package top.hanwg.dsa.search;


import org.junit.jupiter.api.Assertions;

// #200 medium
// Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
// You may assume all four edges of the grid are all surrounded by water.
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        // track cells in the grid which we have visited
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    explore(visited, grid, i, j);
                    answer++;
                }
            }
        }

        return answer;
    }

    // mark adjacent cells as visited
    private void explore(boolean[][] visited, char[][] grid, int x, int y) {
        if (visited[x][y])
            return;

        visited[x][y] = true;

        // check bounds and island is connected

        if (y > 1 && grid[x][y-1] == 1)
            explore(visited, grid, x, y - 1); // up

        if (x < grid.length - 1 && grid[x + 1][y] == 1)
            explore(visited, grid, x + 1, y); // right

        if (y < grid[x].length - 1 && grid[x][y + 1] == 1)
            explore(visited, grid, x , y + 1); // down

        if (x > 1 && grid[x - 1][y] == 1)
            explore(visited, grid, x - 1, y); // left
    }

    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();

        Assertions.assertEquals(1,
                solution.numIslands(new char[][]{
                        {1, 1, 1, 1, 0},
                        {1, 1, 0, 1, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                })
        );

        Assertions.assertEquals(3,
                solution.numIslands(new char[][]{
                        {1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 0, 1, 1}
                })
        );
    }
}
