
// 242 - Valid Anagram

// https://leetcode.com/problems/valid-anagram/

class Solution {
    public boolean isAnagram(String s, String t) {
         if (s == null || t == null || s.length() != t.length()) return false;
        // use 4*26 byte arraylist to store letters
        int[] dict = new int[26]; 
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            dict[ch-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            dict[ch-'a']--;
            if (dict[ch-'a'] < 0) return false;
        }
        return true;
    }
}



// 438. Find All Anagrams in a String
// https://leetcode.com/problems/find-all-anagrams-in-a-string/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<Integer>();
        if (s == null || p == null || s.length() < p.length()) return ret; 
        for (int i = 0; i < s.length() - p.length() + 1; i++){
            String sub = s.substring(i, i + p.length());
            if (isAnagram(sub, p)){
                ret.add(i);
            }
        }
        return ret;
    }
}

// 特别慢了 1.35%


