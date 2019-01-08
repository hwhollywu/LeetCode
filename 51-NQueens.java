51. N-Queens

// https://leetcode.com/problems/n-queens/
/*

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

*/


// Time & Space Complexity: O(n^2)


class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        // initialize board
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList();
        backtracking(board, 0, res);
        return res;
    }
    
    private void backtracking(char[][] board, int col, List<List<String>> res){
        // base case, return one solution
        if (col == board.length){
            res.add(constructlist(board));
            return;
        }
        // recursive case
        for (int i = 0; i < board.length; i++){
            if(valid(board, i, col)){
                board[i][col] = 'Q';
                backtracking(board, col+1, res);
                board[i][col] = '.';
            }
        }
    }
    
    private boolean valid(char[][] board, int r, int c){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < c; j++){ //??? only for built columns
                // for each existing Q, check left, right diagnals, check row
                if (board[i][j] == 'Q' && (r + j == c + i || i + j == r + c || r == i)) return false;
            }
        }
        return true;
    }
    
    
    // from 2D char array to string list
    private List<String> constructlist(char[][] board){
        List<String> res = new ArrayList();
        for (int i = 0; i < board.length; i++){
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}



// https://leetcode.com/problems/n-queens-ii/
/*
Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

// backtracking : Time  O(N!) Space O(N)


class Solution {
    int res = 0;
    
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        // initialize board
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        backtracking(board, 0);
        return res;
    }
    
     private void backtracking(char[][] board, int col){
        // base case, return one solution
        if (col == board.length){
            res++;
            return;
        }
        // recursive case
        for (int i = 0; i < board.length; i++){
            if(valid(board, i, col)){
                board[i][col] = 'Q';
                backtracking(board, col+1);
                board[i][col] = '.';
            }
        }
    }
    
        private boolean valid(char[][] board, int r, int c){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < c; j++){ //??? only for built columns
                // for each existing Q, check left, right diagnals, check row
                if (board[i][j] == 'Q' && (r + j == c + i || i + j == r + c || r == i)) return false;
            }
        }
        return true;
    }
    
    
}

