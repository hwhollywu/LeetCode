833-FindAndReplaceInString

// https://leetcode.com/problems/find-and-replace-in-string/



/*

Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
Output: "eeebffff"
Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
"cd" starts at index 2 in S, so it's replaced by "ffff".

Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation: "ab" starts at index 0 in S, so it's replaced by "eee". 
"ec" doesn't starts at index 2 in the original S, so we do nothing.

Edge case / 难点 : indexes is not an ordered list
"vmokgggqzp"
[3,5,1]
["kg","ggq","mo"]
["s","so","bfr"]

*/


class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder(S); 
        // match array stores if each index in S is a head of replacement
        // if not, match[k] = -1, if yes, stores the order in indexes
        int[] match = new int[S.length()]; 
        Arrays.fill(match,-1);
        // for each index
        for(int i = 0; i < indexes.length; i++){
            // check sources 
            int start = indexes[i];
            int end = start + sources[i].length();
            if (S.substring(start,end).equals(sources[i])){
                match[start] = i;
            }
        }
        // make sure replacement happen in order of string
        int offset = 0;
        for(int k = 0; k < S.length(); k++){
            if (match[k] != -1){
                int i = match[k];
                sb.replace(k + offset, k + offset + sources[i].length(), targets[i]);
                offset += targets[i].length() - sources[i].length();
            }
        }
        
        return sb.toString();
    }
}