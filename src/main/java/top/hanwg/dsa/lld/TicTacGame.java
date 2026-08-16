package top.hanwg.dsa.lld;

public class TicTacGame {

    int currentPlayer = 0;
    int m;

    int[] rows;
    int[] cols;
    int diag = 0;
    int antidiag = 0;

    TicTacGame(int m) {
        this.m = m;
        rows = new int[m];
        cols = new int[m];
    }

    int doMove(int row, int col, int player) {

        validatePlayer(player);
        validatePosition(row, col);

        currentPlayer = player;

        return getWinner(row, col);
    }

    int getWinner(int row, int col) {

        int value = currentPlayer == 1 ? 1 : -1;

        rows[row] += value;
        cols[col] += value;

        if (row == col)
            diag += value;

        if (row + col == m - 1)
            antidiag += value;

        // diag
        // (0,0), (1,1), (2,2), ...

        // antidiag
        // (0, 2), (1, 1), (2, 0), ...

        if (Math.abs(diag) == m ||
                Math.abs(antidiag) == m ||
                Math.abs(rows[row]) == m ||
                Math.abs(cols[col]) == m) {
            return currentPlayer;
        }

        return 0;
    }

    void validatePlayer(int player) {
        if (player != 1 && player != 2) {
            throw new IllegalArgumentException();
        }

        if (player == currentPlayer) {
            throw new IllegalArgumentException();
        }
    }

    void validatePosition(int row, int col) {
        if (row < 0 || row >= m) {
            throw new IllegalArgumentException();
        }

        if (col < 0 || col >= m) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        TicTacGame game = new TicTacGame(3);
        System.out.println(game.doMove(0, 0, 1));

        System.out.println(game.doMove(0,1, 2));

        System.out.println(game.doMove(1, 1, 1));

        System.out.println(game.doMove(0, 2, 2));

        System.out.println(game.doMove(2, 2, 1));
    }
}
