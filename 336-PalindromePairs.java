336-PalindromePairs

// https://leetcode.com/problems/palindrome-pairs/

/*

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]

*/

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList();
        if (words == null) return res;
        // initialize map
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        for (int i = 0; i < words.length; i++){
            // use two pointers
            int l = 0, r = 0;
            while (l <= r){
                String s = words[i].substring(l,r);
                // get reverse string from map
                Integer j = map.get(new StringBuilder(s).reverse().toString());
                String pal = words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l);
                if (j != null && i != j && isPalindrome(pal)){
                    res.add(Arrays.asList(l == 0 ? new Integer[]{i,j} : new Integer[]{j,i}));
                }
                if (r < words[i].length()) r++;
                else l++;
            }
        }
        return res;
    }
    
    
    private boolean isPalindrome(String s){
        int l = 0;
        int r = s.length() - 1;
        while(l <= r){
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}