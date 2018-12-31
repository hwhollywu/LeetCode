79-WordSearch

// https://leetcode.com/problems/word-search/description/


// Given word = "ABCCED", return true.
// Given word = "SEE", return true.
// Given word = "ABCB", return false.

class Solution {
    
    private int nrow;
    private int ncol;
    private final static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) return true;
        nrow = board.length;
        if (nrow == 0) return false;
        ncol = board[0].length;
        boolean[][] visited = new boolean[nrow][ncol];
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (findWord(0, r, c, board, visited, word)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean findWord(int cur, int r, int c, char[][] board, boolean[][] visited, String s){
        // base case: return if found word
        if (cur == s.length()) return true;
        // check border
        if (r < 0 || r >= nrow || c < 0 || c >= ncol || board[r][c] != s.charAt(cur) || visited[r][c]) return false;
        // mark as visited
        visited[r][c] = true;
        // recursive case
        for (int[] d: dirs){
            if (findWord(cur + 1, r + d[0], c + d[1], board, visited, s)) return true;
        }
        // unmark
        visited[r][c] = false;
        return false;
    }
}



// https://leetcode.com/problems/word-search-ii/

/*
Input: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
*/



// use trie



class TrieNode{
      
    private final int size = 26;
    private TrieNode[] children;
    private boolean isEnd;
    
    public TrieNode() {
        children = new TrieNode[size];
        isEnd = false;
    }
    public TrieNode get(char ch){
        return children[ch - 'a'];
    }
    
    public void put(char ch, TrieNode node){
        children[ch - 'a'] = node;
    }
    
    public boolean containsKey(char ch){
        return children[ch - 'a'] != null;
    }
    
    public void setEnd(){
        isEnd = true;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
    
    
}

class Trie {
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char curr = word.charAt(i);
            if (!node.containsKey(curr)){
                node.put(curr, new TrieNode());
            }
            node = node.get(curr);
        }
        node.setEnd();
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char curr = word.charAt(i);
            if (node.containsKey(curr)){
                node = node.get(curr);
            } else return false;
        }
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++){
            char curr = prefix.charAt(i);
            if (node.containsKey(curr)){
                node = node.get(curr);
            } else return false;
        }
        return true;
    }
}

class Solution {
    int nrow;
    int ncol;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    List<String> res;
    
    public List<String> findWords(char[][] board, String[] words) {
        
        Trie trie = new Trie();
        for (String word : words){
            trie.insert(word);
        }
        
        res = new ArrayList();
        nrow = board.length;
        if (nrow == 0) return res;
        ncol = board[0].length;
        boolean[][] visited = new boolean[nrow][ncol];
        for (int r = 0 ; r <= nrow; r++){
            for (int c = 0; c <= ncol; c++){
                // use tries replace String[] words, temperary string 
                findWordsRec("", r, c, board, visited, trie);
            }
        }
        
        return res;
    }
    
    private void findWordsRec(String s, int r, int c, char[][] board, boolean[][] visited, Trie trie){
        // check borders
        if (r < 0 || r >= nrow || c < 0 || c >= ncol || visited[r][c]) return;
        s += board[r][c];
        // base case: return
        if (!trie.startsWith(s)) return;
        if (trie.search(s) && !res.contains(s)) res.add(s);
        visited[r][c] = true;
        for (int[] d : dirs){
            findWordsRec(s, r + d[0], c + d[1], board, visited, trie);
        }
        visited[r][c] = false;
    }

}