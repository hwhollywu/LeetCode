// 200. Number of Islands

// https://leetcode.com/problems/number-of-islands


// Approach 1 DFS O(M×N) #rows, #cols

class Solution {
    public int numIslands(char[][] grid) {
        // base
        if (grid == null || grid.length == 0) return 0;
        int nrow = grid.length;
        int ncol = grid[0].length;
        int res = 0;
        // traversal
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                // only do dfs for islands
                if (grid[r][c] == '1') {
                    res++;
                    dfs(grid, r, c);
                }
            }
        }
        return res;
    }
    
    public void dfs(char[][] grid, int r, int c){
        int nrow = grid.length;
        int ncol = grid[0].length;
        // stop conditions / check borders 
        if (r < 0 || c < 0 || r >= nrow || c >= ncol || grid[r][c] == '0') {
            return;
        }
        // set to zero as this island has been counted
        // mark as visited
        grid[r][c] = '0';
        // check up, down, left, right node
        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);
    } 
}


// Approach 2 BFS  O(M×N) #rows, #cols

class Solution {
    public int numIslands(char[][] grid) {
        // base
        if (grid == null || grid.length == 0) return 0;
        int nrow = grid.length;
        int ncol = grid[0].length;
        int res = 0;
        
        // traversal
        for (int r = 0; r < nrow; r++){
            for (int c = 0; c < ncol; c++){
                // only do bfs for islands
                if (grid[r][c] == '1') {
                    res++;
                    Queue<Integer> q = new LinkedList();
                    // use int r*nc + c to mark the unique node
                    q.add(r*ncol + c);
                    // mark this node as visited 
                    grid[r][c] = '0';
                    while (!q.isEmpty()){
                        int id = q.remove();
                        // get the node r and c
                        int thisr = id / ncol;
                        int thisc = id % ncol;
                        // check neighbors, add if in border
                        if (thisr - 1 >= 0 && grid[thisr-1][thisc] == '1'){
                            q.add((thisr-1)*ncol + thisc);
                            grid[thisr-1][thisc] = '0';
                        }
                        if (thisr + 1 < nrow && grid[thisr+1][thisc] == '1'){
                            q.add((thisr+1)*ncol + thisc);
                            grid[thisr+1][thisc] = '0';
                        }
                        if (thisc - 1 >= 0 && grid[thisr][thisc-1] == '1'){
                            q.add(thisr*ncol + thisc - 1);
                            grid[thisr][thisc-1] = '0';
                        }
                        if (thisc + 1 < ncol && grid[thisr][thisc+1] == '1'){
                            q.add(thisr*ncol + thisc + 1);
                            grid[thisr][thisc+1] = '0';
                        }
                    } 
                }
            }
        }
        return res;
    }
    
}


// Approach 3 Union Find O(M×N) #rows, #cols

// https://leetcode.com/articles/redundant-connection/

class Solution {
  class UnionFind {
    int count; // # of connected components
    int[] parent;
    int[] rank;

    public UnionFind(char[][] grid) { // for problem 200
      count = 0;
      int m = grid.length;
      int n = grid[0].length;
      parent = new int[m * n];
      rank = new int[m * n];
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          if (grid[i][j] == '1') {
            parent[i * n + j] = i * n + j;
            ++count;
          }
          rank[i * n + j] = 0;
        }
      }
    }

    public int find(int i) { // path compression
      if (parent[i] != i) parent[i] = find(parent[i]);
      return parent[i];
    }

    public void union(int x, int y) { // union with rank
      int rootx = find(x);
      int rooty = find(y);
      if (rootx != rooty) {
        if (rank[rootx] > rank[rooty]) {
          parent[rooty] = rootx;
        } else if (rank[rootx] < rank[rooty]) {
          parent[rootx] = rooty;
        } else {
          parent[rooty] = rootx; rank[rootx] += 1;
        }
        --count;
      }
    }

    public int getCount() {
      return count;
    }
  }

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int nr = grid.length;
    int nc = grid[0].length;
    int num_islands = 0;
    UnionFind uf = new UnionFind(grid);
    for (int r = 0; r < nr; ++r) {
      for (int c = 0; c < nc; ++c) {
        if (grid[r][c] == '1') {
          grid[r][c] = '0';
          if (r - 1 >= 0 && grid[r-1][c] == '1') {
            uf.union(r * nc + c, (r-1) * nc + c);
          }
          if (r + 1 < nr && grid[r+1][c] == '1') {
            uf.union(r * nc + c, (r+1) * nc + c);
          }
          if (c - 1 >= 0 && grid[r][c-1] == '1') {
            uf.union(r * nc + c, r * nc + c - 1);
          }
          if (c + 1 < nc && grid[r][c+1] == '1') {
            uf.union(r * nc + c, r * nc + c + 1);
          }
        }
      }
    }

    return uf.getCount();
  }
}
