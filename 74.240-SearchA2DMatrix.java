74. Search a 2D Matrix
// https://leetcode.com/problems/search-a-2d-matrix/

/*
Matrix sorted ; Higher row/column > Lower row/column

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true


Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false

*/

// 2 binary search - TLE
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int nrows = matrix.length;
        int ncols = matrix[0].length;
        // 2D binary search 
        // search rows 
        int rl = 0;
        int rr = nrows - 1;
        while(rl < rr){
            int rm = rl + (rr - rl) / 2;
            //System.out.println("rm:" + rm + "matrix[rm][0]: "+ matrix[rm][0]);
            if(matrix[rm][0] > target){
                rr = rm - 1;
            }else{
                rl = rm;
            }
        }
        //System.out.println("rl:" + rl);
        // rl is the row index
        int cl = 0;
        int cr = ncols - 1;
        while(cl <= cr){
            int cm = cl + (cr - cl) / 2;
            //System.out.println("cm: " + cm + matrix[rl][cm]);
            if(matrix[rl][cm] == target) return true;
            else if (matrix[rl][cm] > target) cr = cm - 1;
            else cl = cm + 1;
        }
        return false;
    }
}

// Approach 1 binary search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
    	// 3 base cases 
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int nrows = matrix.length;
        int ncols = matrix[0].length;
        // binary search 
        int l = 0;
        int r = nrows * ncols - 1;
        while(l < r){
            int m = l + (r - l) / 2;
            if(matrix[m / ncols][m % ncols] < target){
                l = m + 1;
            }else{
                r = m;
            }
        }
        // l is the result 
        return matrix[l / ncols][l % ncols] == target;
    }
}


// Approach 2 pointers 
    public boolean searchMatrix(int[][] matrix, int target) {
  	    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int i = 0; // row index 
        int j = matrix[0].length - 1; // col index 
        while (i < matrix.length && j >= 0) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    j--; // then adjusting cols 
                } else {
                    i++; // first increasing rows 
                }
            }
        return false;
    }



240. Search a 2D Matrix II
// https://leetcode.com/problems/search-a-2d-matrix-ii/

/*

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.

Given target = 20, return false.

*/


// Brute Force Time O(nm), Space O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int nrows = matrix.length;
        int ncols = matrix[0].length;
        for(int i = 0; i < nrows; i++){
            for(int j = 0; j < ncols; j++){
                if(matrix[i][j] == target) return true;
            }
        }
        return false;
    }
}

// Approach Binary Search -> Slow
// Time O(lg(n!)) -> O(nlgn) Space O(1)
class Solution {
    
    int[][] matrix;
    int target;
    int nrows;
    int ncols;
    
    public boolean searchMatrix(int[][] matrix, int target) {
  	    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        this.matrix = matrix;
        this.target = target;
        nrows = matrix.length;
        ncols = matrix[0].length;
        return helper(0, 0, nrows - 1, ncols - 1);
    }
    
    // Recursive 2D binary search 
    public boolean helper(int rl, int cl, int rr, int cr){
        // base case
        // System.out.println("rl :" + rl + " cl: "+ cl + " rr: "+rr + " cr:" + cr + " nrows: " + nrows + " ncols: " + ncols);
        if(rr < rl || cr < cl || rl >= nrows || cl >= ncols || rr < 0 || cr < 0 ) return false;
        int rm = rl + (rr - rl) / 2;
        int cm = cl + (cr - cl) / 2;
        System.out.println("rm: " + rm + "cm: "+ cm + "matrix[rm][cm]:" + matrix[rm][cm]);
        if (matrix[rm][cm] == target) return true;
        else if (matrix[rm][cm] < target){
            // increasing col or row
            // cap column right because repeated search as previous region
            return helper(rl, cm + 1, rr, cr) || helper(rm + 1, cl, rr, cm);
        }else {
            // decreasing col or row
            return helper(rl, cl, rr, cm - 1) || helper(rl, cm, rm - 1, cr);
        }
    }
}



// Approach pointers / search space reduction
// Time O(n + m) Space O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
  	    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int i = 0; // row index 
        int j = matrix[0].length - 1; // col index 
        while (i < matrix.length && j >= 0) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    j--; // then adjusting cols 
                } else {
                    i++; // first increasing rows 
                }
            }
        return false;
    }
}






