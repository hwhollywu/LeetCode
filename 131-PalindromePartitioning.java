131-PalindromePartitioning
// https://leetcode.com/problems/palindrome-partitioning/

/*
Return all possible palindrome partitioning of s.

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

*/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList();
        partitionRec(res, new ArrayList<>(), s, 0);
        return res;
    }
    private void partitionRec(List<List<String>> lists, List<String> temp, String s, int start){
        // base case, return temp if length is the same
        if (start == s.length()) lists.add(new ArrayList<>(temp));
        else{
            for (int i = start; i < s.length(); i++){
                if (isPalindrome(s, start, i)){
                    temp.add(s.substring(start, i+1));
                    partitionRec(lists, temp, s, i+1);
                    temp.remove(temp.size()-1);
                }
            }
        }
        
    }
    private boolean isPalindrome(String s, int l, int r){
        while(l < r){
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    } 
    
}



132-PalindromePartitioningII
// https://leetcode.com/problems/palindrome-partitioning-ii/

/*
Return the minimum cuts needed for a palindrome partitioning of s.

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

*/