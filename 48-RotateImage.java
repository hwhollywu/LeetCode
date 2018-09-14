// 48 Rotate Image
// https://leetcode.com/problems/rotate-image/description/

// ratate for 90 degrees
// note: in-place! 

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i< n/2; i++){
            for (int j = i; j< n-i-1; j ++){
                // rotate for 4 sides
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1] ;
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
            
        }
    }
}

// 396. Rotate Function
// https://leetcode.com/problems/rotate-function/description/

// O(n^2) -> Time Limit Exceeded!
// include Rotate Array
// 坑： MAX不能设置成0，要设置成init value(没有rotate时function的值)

class Solution {
    public int maxRotateFunction(int[] A) {
        int max = computeMax(A);
        // for each rotate number, form a new array rotate array
        for (int k = 1; k < A.length; k++){
            int[] a = rotateArray(A, k);
            int temp = computeMax(a);
            if (temp > max) max = temp;
        }
        return max;
    }
    
    // rotate array
    public int[] rotateArray(int[] array, int k){
        int n = array.length;
        int[] temp = new int[n];
        for (int i = 0; i < n ; i++){
            temp[( i + k ) % n] = array[i];
        } 
        return temp;
    }
    
    // form function
    public int computeMax(int[] array){
        int temp = 0;
        for (int i = 0; i < array.length ; i++){
            temp += i*array[i];
        }
        return temp;
    }
}

//O(n)
// 错位相减法

class Solution {
    public int maxRotateFunction(int[] A) {
        // compute max
        int temp = 0;
        int sum = 0;
        for (int i = 0; i < A.length ; i++){
            temp += i*A[i];
            sum += A[i];
        }
        int max = temp;
        
        // F1=F0-sum+5A(n*item)
        for (int k = 0; k < A.length - 1; k++){
            temp = temp - sum + A.length*A[k];
            if (temp > max) max = temp;
        }
            
        return max;   
    }
}

// 788. Rotated Digits
// https://leetcode.com/problems/rotated-digits/description/

// dynamic programming by 10
// good numbers: 2,5,6,9
// bad numbers: 3,4,7
// numbers that can be rotated(in the middle, not beginning): 1,8,0

class Solution {
  public int rotatedDigits(int N) {
    int total = 0;
    for (int i = 1; i <= N; i++) {
      boolean good = false;
      int j = i;
      outer: while (j > 0) {
        int rem = j % 10;
        switch (rem) {
        case 3:
        case 4:
        case 7:
          good = false;
          break outer;
        case 2:
        case 5:
        case 6:
        case 9:
          good = true;
          break;
        }
        j /= 10;
      }
      if (good) {
        total++;
      }
    }
    return total;
  }