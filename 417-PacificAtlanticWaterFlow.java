// 417 Pacific Atlantic Water Flow

// https://leetcode.com/problems/pacific-atlantic-water-flow/

class Solution {
    private int nrow, ncol;
    private int[][] matrix;
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        // set-up
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0){
            return res;
        }
        nrow = matrix.length;
        ncol = matrix[0].length;
        this.matrix = matrix;
        boolean[][] reachPacific = new boolean[nrow][ncol];
        boolean[][] reachAtlantic = new boolean[nrow][ncol];
        // dfs from borders
        for (int i = 0; i < nrow; i++){
            dfs(i,0,reachPacific);
            dfs(i,ncol-1,reachAtlantic);
        }
        for (int i = 0; i < ncol; i++){
            dfs(0,i,reachPacific);
            dfs(nrow-1,i,reachAtlantic);
        }
        // end travelsal
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (reachPacific[r][c] && reachAtlantic[r][c]){
                    res.add(new int[]{r,c});
                }
            }
        }
        return res;
    }
    
    private void dfs(int r, int c, boolean[][] reach){
        if (reach[r][c] == true){
            return;
        }
        reach[r][c] = true;
        // check if matrix has larger value 
        if (r-1 >= 0 && matrix[r-1][c] >= matrix[r][c]){
            dfs(r-1,c,reach);
        }
        if (r+1 < nrow && matrix[r+1][c] >= matrix[r][c]){
            dfs(r+1,c,reach);
        }
        if (c-1 >=0 && matrix[r][c-1] >= matrix[r][c]){
            dfs(r,c-1,reach);
        }
        if (c+1 < ncol && matrix[r][c+1] >= matrix[r][c]){
            dfs(r,c+1,reach);
        }
        
    }
    
}