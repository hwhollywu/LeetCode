64-MinimumPathSum

// https://leetcode.com/problems/minimum-path-sum/

/*
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int nrow = grid.length;
        int ncol = grid[0].length;
        // increment for row = 0
        for (int i = 1; i < ncol; i++){
            grid[0][i] += grid[0][i-1];
        }
        // increment for col = 0
        for (int i = 1; i < nrow; i++){
            grid[i][0] += grid[i-1][0];
        }
        for (int r = 1; r < nrow; r++){
            for (int c = 1; c < ncol; c++){
                grid[r][c] += Math.min(grid[r-1][c], grid[r][c-1]);
            }
        }
        return grid[nrow-1][ncol-1];
    }
}


62. Unique Paths
// https://leetcode.com/problems/unique-paths/
/*
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
*/


class Solution {
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[n][m];
        // fill array
        for (int[] a : grid){
            Arrays.fill(a,1);   
        }
        for(int r = 1; r < n; r++){
            for(int c = 1; c < m; c++){
                grid[r][c] = grid[r-1][c] + grid[r][c-1];
            }
        }
        return grid[n-1][m-1];
    }
}