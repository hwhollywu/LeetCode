37-SudokuSolver

// https://leetcode.com/problems/sudoku-solver/description/


36-ValidSudoku

class Solution {
    boolean[][] rowUsed = new boolean[9][10]; // 9 = #rows, 10 = #nums
    boolean[][] colUsed = new boolean[9][10];
    boolean[][] boxUsed = new boolean[9][10];
    
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    if(rowUsed[i][num]) return false;
                    rowUsed[i][num] = true;
                    if(colUsed[j][num]) return false;
                    colUsed[j][num] = true;
                    if(boxUsed[(i/3)*3+j/3][num]) return false;
                    boxUsed[(i/3)*3+j/3][num] = true;
                }
            }
        }
        return true;
    }
}


class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return; // base case
        solveSudokuHelper(board);
    }
    
    private boolean solveSudokuHelper(char[][] board){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] == '.'){
                    // backtracking
                    for (char ch = '1'; ch <= '9'; ch++){
                        if (isValid(board, i, j, ch)){
                            board[i][j] = ch;
                            if (solveSudokuHelper(board)) return true;
                            else board[i][j] = '.'; // undo
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char ch){
        for (int k = 0; k < 9; k++){
            // check each row
            if (board[k][col] != '.' && board[k][col] == ch) return false;
            // check each col
            if (board[row][k] != '.' && board[row][k] == ch) return false;
            // check each box
            if (board[(row/3)*3+k/3][(col/3)*3+k%3] != '.' && board[(row/3)*3+k/3][(col/3)*3+k%3] == ch) return false;
        }
        return true;
    }
}
