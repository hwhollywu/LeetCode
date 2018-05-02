// 524. Longest Word in Dictionary through Deleting
// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/

/* java-size:
array: a.length
string: a.length()
ArrayList: a.size()

compare lexicigraphically
int compareTo(String str) :
-> 0 same
-> -1 smaller than by 1
-> 17 larger than by 17
*/

class Solution {
    public String findLongestWord(String s, List<String> d) {
        String result = "";
        // two pointers
        int i = 0, j = 0;
        // for each element in d
        for (int index = 0; index < d.size(); index ++){
            String ds = d.get(index);
            while(i < s.length() && j < ds.length()) {
                // j only increase when it matches with i
                if(s.charAt(i) == ds.charAt(j)) j++;
                // the string matches
                if (j == ds.length()) {
                    // replace result if the current one is longer or has smaller lexicographical order
                    if (result.length() < ds.length() || 
                        (result.length() == ds.length() && result.compareTo(ds) > 0) ) {
                        result = ds;
                    }
                }
                i++;
            }
            i = 0;
            j = 0; //reset
        }
        return result;
    }
}