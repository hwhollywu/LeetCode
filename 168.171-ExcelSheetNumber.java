// 171. Excel Sheet Column Number

// https://leetcode.com/problems/excel-sheet-column-number/

class Solution {
    public int titleToNumber(String s) {
        int result = 0 ;
        for (int i = 0; i < s.length(); i ++){
            result = result * 26 + (s.charAt(i) - 'A' + 1);
        }
        return result;
    }
}

// 168. Excel Sheet Column Title

// https://leetcode.com/problems/excel-sheet-column-title/


class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0){
            n--;
            sb.insert(0,(char)('A'+n%26));
            n /= 26;
        }
        return sb.toString();
    }
}