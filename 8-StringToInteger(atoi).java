// https://leetcode.com/problems/string-to-integer-atoi/

class Solution {
    public int myAtoi(String str) {
        // 0. skip leading whitespaces
        long sum = 0;
        int i = 0;
        str = str.trim();
        char[] chars = str.toCharArray();
        // 1. base case
        if (str == null || str.length() == 0) return 0;
        // 2. sign
        int sign = 1;
        if (chars[i] == '-'){
            sign = -1;
            i++;
        }else if (chars[i] =='+'){
            sign = 1;
            i++;
        }
        // 3.overflow
        for (int j = i; j < chars.length; j++){
            if(!Character.isDigit(chars[j])) return (int)sum*sign;
            sum = sum * 10 + (chars[j] - '0');
            if (sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && (sum * sign) < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int)sum * sign;
        
    }
}