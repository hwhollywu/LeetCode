// 633. Sum of Square Numbers
// https://leetcode.com/problems/sum-of-square-numbers/description/

class Solution {
    public boolean judgeSquareSum(int c) {
        int left = 0, right =(int)Math.sqrt(c);; // right = largest possible number
        while(left <= right){ // must be <= because otherwise excludes the case left=right e.g. 2*2=4
            int sum = left * left + right * right; //can't use left^2
            if (sum == c) return true;
            else if (sum < c) left ++;
            else right--;
        }
        return false;
    }
}