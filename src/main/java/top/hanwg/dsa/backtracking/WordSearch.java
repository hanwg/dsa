package top.hanwg.dsa.backtracking;

import org.junit.jupiter.api.Assertions;


// #79 medium
// Given an m x n grid of characters board and a string word, return true if word exists in the grid.
// The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
// The same letter cell may not be used more than once.
public class WordSearch {

    public boolean exist(char[][] board, String word) {

        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (search(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][] board, String word, boolean[][] visited, int i, int j, int index) {

        if (index >= word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length) {
            return false;
        }

        if (j < 0 || j >= board[i].length) {
            return false;
        }

        if (visited[i][j]) {
            return false;
        }

        if (board[i][j] != word.charAt(index)) {
            return false;
        }

        visited[i][j] = true;

        if (
            search(board, word, visited, i + 1, j, index + 1) ||
            search(board, word, visited, i - 1, j, index + 1) ||
            search(board, word, visited, i, j + 1, index + 1) ||
            search(board, word, visited, i, j - 1, index + 1)
        ) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        WordSearch solution = new WordSearch();

        Assertions.assertTrue(solution.exist(new char[][]{
                {'A', 'B', 'C', 'D'},
                {'S', 'A', 'A', 'T'},
                {'A', 'C', 'A', 'E'}
        }, "CAT"));
    }
}
