// 392. Is Subsequence (Medium)
// https://leetcode.com/problems/is-subsequence/description/

class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        // base case 
        if (s.equals("")) return true;
        while(i < s.length() && j < t.length()){
            if (s.charAt(i) == t.charAt(j)){
                if (i == s.length() -1) return true;
                i++;
            }
            j++;
        }
        return false;
    }
}