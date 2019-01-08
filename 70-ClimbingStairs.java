// 70.  Climbing stairs
// https://leetcode.com/problems/climbing-stairs/solution/

// Approach 2: Recursion with memorization O(n), O(n)
public class Solution {
    public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }
    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}

// Approach 3: O(n), O(n)
public class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1]; // +1 for array indexing
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

// Approach 4: O(N),O(1)
class Solution {
    public int climbStairs(int n) {
        // base case
        if (n <= 2) return n;
        int t1 = 1;
        int t2 = 2;
        int result = 1;
        for (int i = 3; i < n; i++){
            result = t1 + t2;
            t1 = t2;
            t2 = result;
        }
        return result;
    }
}

// Approach 5: Binets Method O(logN),O(1)

public class Solution {
    public int climbStairs(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}


// my own version 

class Solution {
    public int climbStairs(int n) {
        int[][] q = {{1,1},{1,0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }
    
    private int[][] pow(int[][] m, int n){
        int[][] c = {{1,0},{0,1}}; // initialize identity matrix
        while (n > 0){
            if ((n&1) ==1) c = multiply(c, m); // c = m, m^2, m^4 ... m^n
            n >>= 1; // n -> n/2 -> .. -> 2 -> 1
            m = multiply(m, m); // m = m^2
        }
        return c;
    }
    
    private int[][] multiply(int[][] a, int [][] b){
        int[][] c = new int[2][2];
        c[0][0] = a[0][0]*b[0][0] + a[0][1]*b[1][0];
        c[0][1] = a[0][0]*b[1][0] + a[0][1]*b[1][1];
        c[1][0] = a[1][0]*b[0][0] + a[1][1]*b[1][0];
        c[1][1] = a[1][0]*b[1][0] + a[1][1]*b[1][1];
        return c;
    }
}


// Approach 6: Fibonacci Formula O(logN),O(1)
public class Solution {
    public int climbStairs(int n) {
        double sqrt5=Math.sqrt(5);
        double fibn=Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
        return (int)(fibn/sqrt5);
    }
}




