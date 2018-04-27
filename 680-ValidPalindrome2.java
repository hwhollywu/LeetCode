// 680. Valid Palindrome II
// https://leetcode.com/problems/valid-palindrome-ii/description/


class Solution {
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


/*
Wrong Case:
Input:
"aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"
Output: false
should be: true
*/


// with a helper function
// Time:  O(N) N= length of the string
// Space: O(1)

public boolean validPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
        if (s.charAt(i) != s.charAt(j)) {
        	// test the two cases seperately, and return from the helper function
        	// because can only remove once
            return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
        }
        i++;
        j--;
    }
    return true;
}

private boolean isPalindrome(String s, int l, int r) {
    while (l < r) {
    	// return false before actually move the pointer
        if (s.charAt(l++) != s.charAt(r--)) return false;
    }
    return true;
}