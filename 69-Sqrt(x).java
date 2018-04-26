// 69. Sqrt(x)
// https://leetcode.com/problems/sqrtx/description/

// Brute Force w=> Time Limit Exceeded 
class Solution {
    public int mySqrt(int x) {
        int result = 0;
        // base case
        if (0 < x & x < 1){ //-> return x because x can only be 0/1 (integer)
            return 1;
        }
        for (int i = 0; i <= x; i++){
            if (i*i == x){
                return i;
            }else if (i*i > x){
                return i - 1;
            }
        }
        return result; 
    }
}

// 二分算法 Binary Search

class Solution {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        // 0-1 already included in the base case, therefore left starts with 1
        int left = 1, right = x-1;
        while (left <= right){
            // because adding left + right might overflow
            int mid = left + (right - left) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                return sqrt;
            }
            else if (sqrt < mid) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        // only if left > right, return
        // want the result to be truculated, so return the smaller one right
        return right;
    }
}


