9. Palindrome Number
// https://leetcode.com/problems/palindrome-number/


class Solution {
    public boolean isPalindrome(int x) {
        char[] chars = ("" + x).toCharArray();
        int r = chars.length-1;
        int l = 0;
        while(l < r){
            if(chars[l] != chars[r]) return false;
            l++;
            r--;
        }
        return true;
    }
}



public boolean isPalindrome(int x) {
    if (x<0 || (x!=0 && x%10==0)) return false;
    int rev = 0;
    while (x>rev){
    	rev = rev*10 + x%10;
    	x = x/10;
    }
    return (x==rev || x==rev/10);
    // if odd, 121, x = 1, rev = 12, x == rev/10
    // if even, 1221, x = 12, rev = 12, x == rev
}