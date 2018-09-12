// 66. Plus One
// https://leetcode.com/problems/plus-one/description/

// strategy : 倒着iterate


class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // case number 0
        if (digits[0] == 0) return new int[]{1};
        
        for (int i = n-1; i >= 0; i--){
            if (digits[i] < 9){
                // return the original digits with last digit +1;
                // or 19,29 case: add the previous bit when 9 is not the first digit
                digits[i]++;
                return digits;
            }
            // digit 9 case
            digits[i] = 0;
        }
        
        // add one number to the digits, when 9 is the first digit
        int[] result = new int[n+1];
        result[0] = 1;
        return result;
    }   
}