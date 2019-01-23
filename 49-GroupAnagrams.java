49. Group Anagrams

// https://leetcode.com/problems/group-anagrams/


// Complexity O(NKlogK) N = number of strs K = max length of a string in strs
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> map = new HashMap<>();
        for(String s : strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars); // nlogn
            String key = String.valueOf(chars);
            if(!map.contains(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}


// Complexity O(NK)

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> map = new HashMap<>();
        for(String s : strs){

            int[] count = new int[26];
            for(char c : s.toCharArray()){
                count[c - 'a']++;
            }           
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < 26; i++){
                sb.append(count[i] + ' ');
            }
            String key = sb.toString();
            if(!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}