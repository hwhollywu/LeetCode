542-01Matrix

//https://leetcode.com/problems/01-matrix/


// Wrong approach: DFS 
class Solution {
    private int nrow = 0;
    private int ncol = 0;
    public int[][] updateMatrix(int[][] matrix) {
        nrow = matrix.length;
        ncol = matrix[0].length;
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (matrix[r][c] != 0){
                    dfs(matrix, r, c);
                }
            }
        }
        return matrix;
    }
    
    private void dfs(int[][] matrix, int r, int c){
        if (matrix[r][c] == 0) return;
        int left = (r - 1 < 0) ? Integer.MAX_VALUE : matrix[r-1][c];
        int right = (r + 1 >= nrow) ? Integer.MAX_VALUE : matrix[r+1][c];
        int up = (c - 1 < 0) ? Integer.MAX_VALUE : matrix[r][c-1];
        int down = (c + 1 >= ncol) ? Integer.MAX_VALUE : matrix[r][c+1];
        matrix[r][c] = Math.min(Math.min(left,right), Math.min(up,down)) + 1;
        if (left != Integer.MAX_VALUE) dfs(matrix, r-1, c);
        if (right != Integer.MAX_VALUE) dfs(matrix, r+1, c);
        if (up != Integer.MAX_VALUE) dfs(matrix, r, c-1);
        if (down != Integer.MAX_VALUE) dfs(matrix, r, c+1);
    }
}


// BFS Approach: *initiate every non-zero to max first, do bfs from every 0 node
// Time & Space O(r⋅c)

class Solution {
    
    public int[][] updateMatrix(int[][] matrix) {
        int nrow = matrix.length;
        int ncol = matrix[0].length;
        Queue<Integer> q = new LinkedList();
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (matrix[r][c] == 0){
                    q.offer(r*ncol + c);
                }else matrix[r][c] = Integer.MAX_VALUE;
            }
        }
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!q.isEmpty()){
            int node = q.poll();
            int r = node / ncol;
            int c = node % ncol;
            // for each neighbor
            for (int[] d : dir){
                int nextr = r + d[0];
                int nextc = c + d[1];
                if (nextr < 0 || nextr >= nrow || nextc < 0 || nextc >= ncol || matrix[nextr][nextc] <= matrix[r][c] ) continue;
                q.offer(nextr * ncol + nextc);
                matrix[nextr][nextc] = matrix[r][c] + 1;
            }
        }
        
        return matrix;
    }
    

}



// DP Approach: 
// Time & Space O(r⋅c)


public int[][] updateMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
        return matrix;
    }
    int[][] dis = new int[matrix.length][matrix[0].length];
    int range = matrix.length * matrix[0].length;
    
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i][j] == 0) {
                dis[i][j] = 0;
            } else {
                int upCell = (i > 0) ? dis[i - 1][j] : range;
                int leftCell = (j > 0) ? dis[i][j - 1] : range;
                dis[i][j] = Math.min(upCell, leftCell) + 1;
            }
        }
    }
    
    for (int i = matrix.length - 1; i >= 0; i--) {
        for (int j = matrix[0].length - 1; j >= 0; j--) {
            if (matrix[i][j] == 0) {
                dis[i][j] = 0;
            } else {
                int downCell = (i < matrix.length - 1) ? dis[i + 1][j] : range;
                int rightCell = (j < matrix[0].length - 1) ? dis[i][j + 1] : range;
                dis[i][j] = Math.min(Math.min(downCell, rightCell) + 1, dis[i][j]);
            }
        }
    }
    
    return dis;
}
