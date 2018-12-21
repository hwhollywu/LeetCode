// 695. Max Area of Island

// https://leetcode.com/problems/max-area-of-island/description/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int nrow = grid.length;
        int ncol = grid[0].length;
        int area = 0;
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (grid[r][c] == 1){
                    int thisArea = dfs(grid,r,c,0);
                    area = Math.max(area, thisArea);
                }
            }
        }
        return area;
    }
    
    public int dfs(int[][]grid, int row, int col, int area){
        int nrow = grid.length;
        int ncol = grid[0].length;
        // check border / validity
        if (row < 0 || row >= nrow || col < 0 || col >=ncol || grid[row][col] == 0){
            return area;
        }
        // mark as visited
        grid[row][col] = 0;
        area++;
        area = dfs(grid, row-1, col, area);
        area = dfs(grid, row+1, col, area);
        area = dfs(grid, row, col-1, area);
        area = dfs(grid, row, col+1, area);
        return area;
    }
}