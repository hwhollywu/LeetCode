771. Jewels and Stones

// https://leetcode.com/problems/jewels-and-stones/

/*
Input: J = "aA", S = "aAAbbbb"
Output: 3 => because S has a, A, A = 3
Input: J = "z", S = "ZZ"
Output: 0
*/

// Time O(J + S), Space O(J)

class Solution {
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        // store j
        Set<Character> js = new HashSet();
        for(char c : J.toCharArray()) js.add(c);
        // find j in s 
        for(char c : S.toCharArray()){
            if(js.contains(c)) res++;
        }
        return res;
    }
}