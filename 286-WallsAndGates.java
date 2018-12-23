// 286. Walls and Gates

// https://leetcode.com/problems/walls-and-gates/
class Solution {
    public void wallsAndGates(int[][] rooms) {
        // use BFS because need to find the shortest distance from gate
        // start with 0 values, BFS neighbor values 
        final int[][] directions = {{1,0},{-1,0},{0,-1},{0,1}};
        final int nrow = rooms.length;
        if (nrow ==0) return; // base case 
        final int ncol = rooms[0].length;
        Queue<Integer> q = new LinkedList();
        Set<Integer> s = new HashSet();
        // first, adding all roots
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                if (rooms[r][c] == 0){
                    // add root 
                    q.add(r*ncol + c);
                }
            }
        }
        // then traverse 
        while(!q.isEmpty()){                        
            int node = q.remove();
            int noder = node / ncol;
            int nodec = node % ncol;
            // add neighbors 
            for (int[] d : directions){
                int nr = noder + d[0];
                int nc = nodec + d[1];
                if (nr < 0 || nr >= nrow || nc < 0 || nc >= ncol || rooms[nr][nc] !=  Integer.MAX_VALUE){
                    continue;
                }
                // update value!
                rooms[nr][nc] = rooms[noder][nodec] + 1;
                q.add(nr*ncol + nc);       
            }                        
        }
              
    }
}