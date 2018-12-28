126-WordLadderII
// https://leetcode.com/problems/word-ladder-ii/

// 1 Use BFS to construct a graph. 
// Using a MAP to store the min ladder of each word or a set 
// 2 Use DFS to construct the paths from end to start

class Solution {
    List<List<String>> results;
    HashMap<String, List<String>> map;
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        Queue<String> q = new LinkedList(); // words used 
        // HashSet<String> set = new HashSet(wordList); // words to be used 
        results = new ArrayList<List<String>>();
        map = new HashMap(); // store the min ladder for each word
        HashMap<String, Integer> ladder = new HashMap(); // length for each word
        if (wordList.size() == 0) return results;
        int min = Integer.MAX_VALUE;
        
        // init ladder, words to be used 
        for(String s : wordList){
            ladder.put(s, Integer.MAX_VALUE);
        }
        ladder.put(beginWord, 0);
        
        q.add(beginWord);
        while (!q.isEmpty()){
            String cur = q.poll();
            int step = ladder.get(cur) + 1; // increment step;
            
            // for each possible next word
            for (int i = 0; i < endWord.length(); i++){
                for (char ch = 'a' ; ch <= 'z'; ch++){
                    StringBuilder sb = new StringBuilder(cur);
                    sb.setCharAt(i, ch);
                    String next = sb.toString();
                    if (ladder.containsKey(next)){
                        // if it is not the shortest path to the word
                        if (step>ladder.get(next)){
                            continue;
                        }else if (step<ladder.get(next)){
                            q.add(next);
                            ladder.put(next, step);
                        }else; // ** If one word already appeared in one ladder, do not insert the same word twice; same step 
                            
                        if (map.containsKey(next)){
                            map.get(next).add(cur); // add current word to the list
                        }else{
                            // build new list
                            List<String> list = new LinkedList<String>();                                       list.add(cur);
                            map.put(next, list);
                        }
                        
                        // target
                        if (next.equals(endWord)){
                            min = step; // update min
                        }
                                                
                    }
                }
            }
        }
        // backtracking
        LinkedList<String> result = new LinkedList();
        backTrace(endWord,beginWord,result);
        return results;
    }
    
    
    private void backTrace(String word, String start, List<String> list){
        if (word.equals(start)){
            list.add(0, start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if(map.get(word)!=null){
            for (String s:map.get(word)){
                backTrace(s, start, list);
            }
        }
        list.remove(0);
    }
    
}
