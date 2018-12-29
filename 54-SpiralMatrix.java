54-SpiralMatrix

// https://leetcode.com/problems/spiral-matrix/

class Solution {
    
    HashSet<Integer> set;
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}}; // spiral directions
    int nrow = 0;
    int ncol = 0;
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        nrow = matrix.length;
        if(nrow == 0) return res;
        ncol = matrix[0].length;
        if (ncol == 0) return res; 
        set = new HashSet();
        spiralOrderHelper(matrix, 0, 0, 0, res);
        return res;
    }
    
    private void spiralOrderHelper(int[][] matrix, int r, int c, int dir, List<Integer> res){
        // mark as visited
        set.add(r*ncol + c);
        res.add(matrix[r][c]);
        // move to the neighbor
        int[] nextdir = dirs[dir];
        int nextr = r + nextdir[0];
        int nextc = c + nextdir[1];
        // check full size
        if (set.size() == nrow * ncol){
            return;
        } 
        // check border & direction
        if (nextr < 0 || nextr >= nrow || nextc < 0 || nextc >= ncol || set.contains(nextr * ncol + nextc)) {
            // update direction
            if (dir == 3) dir = 0;
            else dir++;
            nextdir = dirs[dir];
            nextr = r + nextdir[0];
            nextc = c + nextdir[1];
        }
        spiralOrderHelper(matrix, nextr, nextc, dir, res);
    }
    
}


// Approach 1: Simulation O(N)


class Solution {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        int nrow = matrix.length;
        if(nrow == 0) return res;
        int ncol = matrix[0].length;
        if (ncol == 0) return res; 
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}}; // spiral directions
        boolean[][] seen = new boolean[nrow][ncol]; // check visited
        int r = 0;
        int c = 0;
        int di = 0;
        // iterate through each node 
        for(int i = 0; i < nrow * ncol; i++){
            res.add(matrix[r][c]);
            seen[r][c] = true;
            int[] nextdir = dirs[di];
            int nextr = r + nextdir[0];
            int nextc = c + nextdir[1];
            if (nextr < 0 || nextr >= nrow || nextc < 0 || nextc >= ncol || seen[nextr][nextc]) {
                di = (di + 1) % 4;
                nextdir = dirs[di];
                r += nextdir[0];
                c += nextdir[1];
            }else{
                r = nextr;
                c = nextc;
            }
        }
        
        return res;
    }

}

// Approach 2: layer by layer O(N)

class Solution {
    public List < Integer > spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
}


59-SpriralMatrixii
// https://leetcode.com/problems/spiral-matrix-ii/


class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int r = 0;
        int c = 0;
        int num = 1;
        int di = 0;
        for (int i = 0; i < n * n; i++){
            matrix[r][c] = num;
            num++;
            int nextr = r + dirs[di][0];
            int nextc = c + dirs[di][1];
            if (nextr < 0 || nextr >= n || nextc < 0 || nextc >= n || matrix[nextr][nextc] != 0){
                di = (di + 1) % 4;
                r += dirs[di][0];
                c += dirs[di][1];
            }else{
                r = nextr;
                c = nextc;
            }
        }
        
        return matrix;
        
    }
}
