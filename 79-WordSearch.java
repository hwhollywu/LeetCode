79-WordSearch

// https://leetcode.com/problems/word-search/description/


// Given word = "ABCCED", return true.
// Given word = "SEE", return true.
// Given word = "ABCB", return false.

class Solution {
    
    private int nrow;
    private int ncol;
    private final static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) return true;
        nrow = board.length;
        if (nrow == 0) return false;
        ncol = board[0].length;
        boolean[][] visited = new boolean[nrow][ncol];
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (findWord(0, r, c, board, visited, word)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean findWord(int cur, int r, int c, char[][] board, boolean[][] visited, String s){
        // base case: return if found word
        if (cur == s.length()) return true;
        // check border
        if (r < 0 || r >= nrow || c < 0 || c >= ncol || board[r][c] != s.charAt(cur) || visited[r][c]) return false;
        // mark as visited
        visited[r][c] = true;
        // recursive case
        for (int[] d: dirs){
            if (findWord(cur + 1, r + d[0], c + d[1], board, visited, s)) return true;
        }
        // unmark
        visited[r][c] = false;
        return false;
    }
}




// https://leetcode.com/problems/word-search-ii/