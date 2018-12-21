// 547. Friend Circles

// https://leetcode.com/problems/friend-circles/

// 和Number of Island的不同：对称，N*N表达i和j Pair的关系 
// 用boolean[] 表示visited关系，循环一遍就好 
// Time O(n^2), space O(n)

// DFS

class Solution {
    public int findCircleNum(int[][] M) {
        int count = 0;
        int n = M.length;
        boolean[] hasVisited = new boolean[n];
        for (int i = 0; i< n; i ++){
                if (!hasVisited[i]){
                    count++;
                    dfs(M, i, hasVisited);
                }
            }
        return count;
    }
    

    private void dfs(int[][] M, int i, boolean[] hasVisited) {
        int n = M.length;
        for (int j = 0; j < n; j++){
            if (M[i][j] == 1 && !hasVisited[j]){ // O(N^2)
                hasVisited[j] = true; // mark as visited
                dfs(M, j, hasVisited);
            }
        }
        
    }
}

// BFS

public class Solution {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue < Integer > queue = new LinkedList < > ();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0)
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }
}

