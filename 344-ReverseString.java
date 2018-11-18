// 344. Reverse String
// https://leetcode.com/problems/reverse-string/

class Solution {
    public String reverseString(String s) {
        StringBuilder st = new StringBuilder();
        for (int i = s.length() - 1; i >= 0 ; i--){
            st.append(s.charAt(i));
        }
        return st.toString();
        // or use string builder: StringBuilder sb = new StringBuilder(s);
        // return sb.reverse().toString();
    }
}

// not use stringbuilder/inplace, swap chars with two pointers
// beat 20% -> beat 80%
class Solution {
    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            // swap chars
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(chars);
    }
}

