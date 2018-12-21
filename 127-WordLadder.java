// 127. Word Ladder

// https://leetcode.com/problems/word-ladder/description/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>(); // stores the already used words
        HashSet<String> set = new HashSet<>(wordList); // stores the words to be used
        q.add(beginWord);
        int length = 0;
        while (!q.isEmpty()){
            int size = q.size();
            length++;
            while (size-- > 0){
                String cur = q.poll();
                for (int i = 0; i < endWord.length(); i++){
                     // for each replacement of char
                    for (char ch = 'a'; ch <= 'z'; ch++){
                    StringBuilder sb = new StringBuilder(cur);
                    sb.setCharAt(i, ch);
                        // if the new word is in the list 
                        if (set.contains(sb.toString())){
                            //System.out.println(sb.toString());
                            // base case: return
                            if(sb.toString().equals(endWord)){
                                return length+1;
                            }
                            q.add(sb.toString());
                            set.remove(sb.toString());
                        }
                    
                    }
                }
            }
        }
        // base case: transformation not found
        return 0;
    }
}