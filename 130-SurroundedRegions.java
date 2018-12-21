// 130. Surrounded Regions

// https://leetcode.com/problems/surrounded-regions/

class Solution {
    public void solve(char[][] board) {
        // base case
        if (board == null || board.length == 0){
            return;
        }
        int nrow = board.length;
        int ncol = board[0].length;
        // dfs for the borders, replace O with T (visited)
        for(int i = 0; i < ncol; i++){
            dfs(board, 0, i);
            dfs(board, nrow-1, i);
        }
        
        for (int i = 0; i < nrow; i++){
            dfs(board, i, 0);
            dfs(board, i, ncol - 1);
        }
        // for the all regions, replace O with X, T with O
        for(int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (board[r][c] == 'T'){
                    board[r][c] = 'O';
                }else if (board[r][c] == 'O'){
                    board[r][c] = 'X';
                }
            }
        }
    }
    
    private void dfs(char[][]board, int r, int c){
        int nrow = board.length;
        int ncol = board[0].length;
        // base case, check for both X & T
        if (r < 0 || r >= nrow || c < 0 || c >= ncol || board[r][c] != 'O'){
            return;
        }
        board[r][c] = 'T'; // mark as visited
        dfs(board, r+1, c);
        dfs(board, r-1, c);
        dfs(board, r, c+1);
        dfs(board, r, c-1);
    }
    
}