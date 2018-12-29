58-LengthOfLastWord

// https://leetcode.com/problems/length-of-last-word/

// testcase "a " : ending with blank space
// solution : scan from back instead s

class Solution {
    public int lengthOfLastWord(String s) {
        if (s.length() == 0) return 0;
        int count = 0;
        for (int i = s.length() -1 ; i >= 0 ; i--){
            if (s.charAt(i) != ' '){
                count++;
            }else if (count == 0){
                // if at end ' ' of string
                continue;
            }else break; // if at middle ' ', return
        }
        return count;
    }
}