// 5. Longest Palindromic Substring
// https://leetcode.com/problems/longest-palindromic-substring/

// Brute Force - O(n^3) => 不对  abb reversed = bba的情况


class Solution {
    public String longestPalindrome(String s) {
        // base cases
        if (s == null || s.length() == 0) return s;
        if (s.length() == 1) return s;
        // reverse s
        StringBuilder rev = new StringBuilder(s);
        String reversed = rev.reverse().toString();
        // find the longgest common substring between s and reversed
        String res = ""+s.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length();i++){
            if (s.charAt(i) == reversed.charAt(i)){
                sb.append(s.charAt(i));
            }
            if (sb.length() > res.length() && validPalindrome(sb.toString())){
                res = sb.toString();
            }
        }
        return res;
       
    }
    
    
    public boolean validPalindrome(String s) {
        // base case
        if (s.length() == 0) return false;
        if (s.length() == 1) return true;
        int left = 0, right = s.length() - 1;
        boolean has_delete = false;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                if (!has_delete){
                    has_delete = true;
                    // choose to move left or right pointer
                    if (s.charAt(left + 1) == s.charAt(right)) left++; 
                    else right--;
                }else return false;
            }else{
                left++;
                right--;
            }
        }
        return true;
    }
}



// Using DP: O(n^2)

public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}