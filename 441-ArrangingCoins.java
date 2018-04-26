// 441. Arranging Coins
// https://leetcode.com/problems/arranging-coins/


// Brute-force  => Time Exceed Limit
class Solution {
    public int arrangeCoins(int n) {
        int sum = 0;
        if (n <= 1){
            return n;
        }
        for (int i= 1; i <= n; i++){
            sum += i;
            if (sum == n){
                return i;
            }
            else if (sum > n){
                return (i - 1);
            }
        }
        return -1;
    }
}
// Binary Search
class Solution {
    public int arrangeCoins(int n) {
        int left = 0, right = n;
        while (left <= right){
            int mid = left + (right - left) / 2;
            long x = mid * (mid + 1L) / 2;
            if (x == n) return mid;
            // when x < n, find in a larger range 
            else if (x<n) left = mid + 1;
            else right = mid -1;
            }
        // return when left > right, return the smaller value
        return right;
    }
}