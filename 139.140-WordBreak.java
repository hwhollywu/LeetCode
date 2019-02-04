139. Word Break
// https://leetcode.com/problems/word-break/

/*
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

// Approach : Brute Force / backtracking, try one at a time 
// Time O(N^N) Space O(N) N = # words in dict
// TLE!! 

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // construct hashset
        Set<String> set = new HashSet<>(wordDict);
        return wordBreakRec(s, set, 0);
    }
    
    public boolean wordBreakRec(String s, Set<String> set, int i){
        // base case
        if (i == s.length()) return true;
        // recursive case (add + remove)
        for(int j = i + 1; j <= s.length(); j++){
            String sub = s.substring(i, j);
            if(set.contains(sub) && wordBreakRec(s, set, j)) return true;
        }
        return false;
    }
}

// Approach Recursion with memoization *
// Time O(N^2) Space O(N)

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // construct hashset
        Set<String> set = new HashSet<>(wordDict);
        // use a boolean array to store the res value through string, init as null values
        Boolean[] bool = new Boolean[s.length()];
        // boolean[] bool = new boolean[size] -> init as false
        return wordBreakRec(s, set, 0, bool);
    }
    
    public boolean wordBreakRec(String s, Set<String> set, int i, Boolean[] bool){
        // base case
        if (i == s.length()) return true;
        if (bool[i] != null) return bool[i];
        // recursive case (add + remove)
        for(int j = i + 1; j <= s.length(); j++){
            String sub = s.substring(i, j);
            if(set.contains(sub) && wordBreakRec(s, set, j, bool)){
                return bool[i] = true;
            } 
        }
        return bool[i] = false;
    }
}


// Approach BFS with memoization (instead of DFS Recursion)
// Time O(N^2) Space O(N)

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // construct hashset
        Set<String> set = new HashSet<>(wordDict);
        // use a queue to store positions of each word break
        Queue<Integer> q = new LinkedList<>();
        // record position
        boolean[] visited = new boolean[s.length()];
        q.add(0);
        while(!q.isEmpty()){
            int start = q.poll();
            if (visited[start] == false){
                for(int end = start + 1; end <= s.length(); end++){
                    if(set.contains(s.substring(start, end))){
                        q.add(end);
                        if(end == s.length()) return true;
                    }
                }
            }
            visited[start] = true; // mark as visited
        }
        return false;
    }
}

// Approach DP
// Time O(N^2) Space O(N)

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // construct hashset
        Set<String> set = new HashSet<>(wordDict);
        // use array to record visited status
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++){ // end
            for(int j = 0; j < i; j++){ // start
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}



140. Word Break II
// https://leetcode.com/problems/word-break-ii/

/*
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

*/


// Approach Recursion with memoization *
// Time O(N^3) Space O(N^3)

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return wordBreakRec(s, set, 0);
    }
    // use a hashmap to store starting index and corresponding res;
    Map<Integer, List<String>> map = new HashMap<>();
    
    public List<String> wordBreakRec(String s, Set<String> set, int start){
        // base case for map, if map has value 
        if (map.containsKey(start)){
            return map.get(start);
        }
        // recursive case for map, construct new value
        List<String> res = new ArrayList<String>();
        // base case for res
        if (start == s.length()) res.add(""); 
        // recursive case for res
        for(int end = start + 1; end <= s.length(); end++){
            String sub = s.substring(start,end);
            if(set.contains(sub)){
                List<String> list = wordBreakRec(s, set, end);
                for(String str : list){
                    // concatenate with space BEFORE 
                    String l = str.equals("") ? "" : " " + str;
                    // add to res 
                    res.add(sub + l);
                }
            }
        }
        // add to map
        map.put(start, res);
        return res;
    }
}

