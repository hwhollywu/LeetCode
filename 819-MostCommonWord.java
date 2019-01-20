819. Most Common Word

// https://leetcode.com/problems/most-common-word/

/*
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
*/

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        HashSet<String> set = new HashSet();
        for (String s : banned){
            set.add(s);
        }
        // to store words and frequency 
        HashMap<String, Integer> map = new HashMap();
        char[] arr = paragraph.toCharArray();
        int start = 0;
        for(int i = 0; i < arr.length; i++){
            if (!Character.isLetter(arr[i])){
                // add word
                String word = paragraph.substring(start, i).toLowerCase();
                start = i + 1;
                if(!set.contains(word) && !word.isEmpty()){
                    // System.out.println(word);
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
        }
        // get the word with highest frequency
        Map.Entry<String, Integer> max = null;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(max == null || entry.getValue() > max.getValue()){
                max = entry;
            }
        }
        return max.getKey();
    }
}