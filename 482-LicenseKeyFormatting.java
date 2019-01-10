482-LicenseKeyFormatting

// https://leetcode.com/problems/license-key-formatting/
/*

"2-4A0r7-4k" ， k = 4 
"24A0-R74K"

"2-4A0r7-4k" 
K = 3 
"24-A0R-74K" 

 S = "r" and K = 1
 "R"
*/



class Solution {
    public String licenseKeyFormatting(String S, int K) {
        //1.to upper case, remove dashes
        S = S.toUpperCase();
        S = S.replaceAll("-","");
        //0. base case / must be after 1 for edge case "---"
        if (S.length() == 0) return "";
        //2.insert first dash
        int first = S.length() % K;
        StringBuilder sb = new StringBuilder(S);
        sb.insert(first, "-");
        // 3.while loop, insert following dashes
        int i = first; // index for inserting
        while( i + K + 1 < sb.length()){
            i += K + 1;
            sb.insert(i, "-");
        }
        S = sb.toString();
        // 4.remove dashes at beginning/end
        if (S.charAt(0) == '-') S = S.substring(1);
        if (S.charAt(S.length() - 1) == '-') S = S.substring(0, S.length() - 1);
        return S;
    }
}