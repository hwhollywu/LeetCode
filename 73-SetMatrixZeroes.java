73. Set Matrix Zeroes

// https://leetcode.com/problems/set-matrix-zeroes/

/*
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]

Input:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]


*/

// Approach Time O(m*n) rows * cols
// Space O(m + n) not in place 

class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int nrows = matrix.length;
        int ncols = matrix[0].length;
        // store row/col numbers
        Set<Integer> rset = new HashSet<>();
        Set<Integer> cset = new HashSet<>();
        for(int i = 0; i < nrows; i++){
            for (int j = 0; j < ncols; j++){
                if(matrix[i][j] == 0){
                    rset.add(i);
                    cset.add(j);
                }
            }
        }
        // set zeros
        for(int i = 0; i < nrows; i++){
            for(int j = 0; j < ncols; j++){
                if(rset.contains(i) || cset.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
        
    }
        
}

// Time O((m*n)(m+n)) Space O(1) in place 

class Solution {
    public void setZeroes(int[][] matrix) {
        int MARK =  -1000000;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int nrows = matrix.length;
        int ncols = matrix[0].length;
        for(int i = 0; i < nrows; i++){
            for (int j = 0; j < ncols; j++){
                if(matrix[i][j] == 0){
                    // traverse row and column, set marked 
                    // can't set zeros yet because will affect loop condition
                    for(int k = 0; k < nrows; k++){
                        if(matrix[k][j] != 0){
                            matrix[k][j] = MARK;
                        } 
                    }
                    for(int k = 0; k < ncols; k++){
                        if(matrix[i][k] != 0){
                            matrix[i][k] = MARK;
                        } 
                    }
                }
            }
        }
        
        for(int i = 0; i < nrows; i++){
            for (int j = 0; j < ncols; j++){
                if(matrix[i][j] == MARK) matrix[i][j] = 0;
            }
        }
    }
        
}


// Time O((m*n) Space O(1)

class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int nrows = matrix.length;
        int ncols = matrix[0].length;
        boolean set = false;
        // use the first row and first column to represent the zero status 
        for(int i = 0; i < nrows; i++){
            if(matrix[i][0] == 0) set = true; //represent first column
            for (int j = 1; j < ncols; j++){ // skip first ele
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0; // set col 
                    matrix[i][0] = 0; // set row
                }
            }
        }
        // set zeros
        for(int i = 1; i < nrows; i++){
            for (int j = 1; j < ncols; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        //check first row status
        if(matrix[0][0] == 0){
            for(int j = 0; j < ncols; j++){
                matrix[0][j] = 0;
            }
        }
        // check first col status
        if(set){
            for(int i = 0; i < nrows; i++){
                matrix[i][0] = 0;
            }
        }
    }
        
}
