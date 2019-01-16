76. Minimum Window Substring

// https://leetcode.com/problems/minimum-window-substring/

/*
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
*/


// Approach : Slinding Window
// Complexiy O(N)?

class Solution {
    public String minWindow(String s, String t) {
        if(t.length() > s.length()) return "";
        Map<Character, Integer> map = new HashMap();
        for(char c : t.toCharArray()){
            if(map.get(c) != null){
                map.put(c, map.get(c) + 1);
            }else{
                map.put(c, 1);
            }
        }
        // #chars matching in target string
        int counter = map.size(); 
        int begin = 0;
        int end = 0;
        int head = 0; // result string
        int len = Integer.MAX_VALUE;
        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) counter--;
            }
            end++;
            while(counter == 0){ // find matching substring, try moving begin
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0) counter++; // break while loop
                }
                // update result string
                if(end - begin < len){
                    len = end - begin;
                    head = begin;
                }
                begin++;
            }
        }
        if (len == Integer.MAX_VALUE) return "";
        else return s.substring(head, head+len);
    }
}