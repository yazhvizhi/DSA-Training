package day19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    //N Queens
    static List<List<String>> nQueensResult = new ArrayList<>();

    static boolean isSafe(int[] pos, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (pos[i] == col || Math.abs(pos[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    static void solveNQueens(int row, int n, int[] pos) {
        if (row == n) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(pos[i] == j ? 'Q' : '.');
                }
                board.add(sb.toString());
            }
            nQueensResult.add(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(pos, row, col)) {
                pos[row] = col;
                solveNQueens(row + 1, n, pos);
            }
        }
    }

    static List<List<String>> solveNQueens(int n) {
        nQueensResult = new ArrayList<>();
        int[] pos = new int[n];
        solveNQueens(0, n, pos);
        return nQueensResult;
    }

    //Rat in maze
    static List<String> ratInMazeResult = new ArrayList<>();

    static void solveRatMaze(int i, int j, int[][] mat, int[][] vis, int n, String path) {
        if (i == n - 1 && j == n - 1) {
            ratInMazeResult.add(path);
            return;
        }

        vis[i][j] = 1;
        if (i + 1 < n && mat[i + 1][j] == 1 && vis[i + 1][j] == 0) {
            solveRatMaze(i + 1, j, mat, vis, n, path + "D");
        }
        if (j - 1 >= 0 && mat[i][j - 1] == 1 && vis[i][j - 1] == 0) {
            solveRatMaze(i, j - 1, mat, vis, n, path + "L");
        }
        if (j + 1 < n && mat[i][j + 1] == 1 && vis[i][j + 1] == 0) {
            solveRatMaze(i, j + 1, mat, vis, n, path + "R");
        }
        if (i - 1 >= 0 && mat[i - 1][j] == 1 && vis[i - 1][j] == 0) {
            solveRatMaze(i - 1, j, mat, vis, n, path + "U");
        }
        vis[i][j] = 0;
    }

    static List<String> ratInMaze(int[][] mat) {
        int n = mat.length;
        if (mat[0][0] == 0) {
            return new ArrayList<>();
        }
        int[][] vis = new int[n][n];
        ratInMazeResult = new ArrayList<>();
        solveRatMaze(0, 0, mat, vis, n, "");
        Collections.sort(ratInMazeResult);
        return ratInMazeResult;
    }

    //Sudoku Solver
    static boolean solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValidSudoku(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValidSudoku(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;
            if (board[i][col] == c) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("N-Queens solutions:");
        List<List<String>> queens = solveNQueens(4);
        for (List<String> board : queens) {
            System.out.println(board);
        }

        System.out.println("\nRat in Maze paths:");
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        System.out.println(ratInMaze(maze));

        System.out.println("\nSudoku solution:");
        char[][] sudoku = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(sudoku);
        for (char[] row : sudoku) {
            System.out.println(new String(row));
        }
    }
}