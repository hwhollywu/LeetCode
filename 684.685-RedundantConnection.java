684. Redundant Connection

// https://leetcode.com/problems/redundant-connection/description/

/*

Return an edge that can be removed so that the resulting graph is 
a tree of N nodes. If there are multiple answers, return the answer 
that occurs last in the given 2D-array. 

Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

*/


// class union find

     class UnionFind {
        Map<Integer, Integer> map = new HashMap<>();

        int find(int x){
            if(!map.containsKey(x)) map.put(x, x);

            if(map.get(x)==x) return x;
            int par = find(map.get(x));
            map.put(x, par);
            return par;
        }

        boolean union(int x, int y){
            int px = find(x);
            int py = find(y);

            if(px==py) return false;
            map.put(px, py);
            return true;
        }        
    }
// ---------------------------------------------------
    
    static class DisjointSet {
        
        private int[] parent;
        private byte[] rank;
        
        public DisjointSet(int n) {
            if (n < 0) throw new IllegalArgumentException();
            parent = new int[n];
            rank = new byte[n];
        }
        
        public int find(int x) {
            if (parent[x] == 0) return x;
            return parent[x] = find(parent[x]); // Path compression by halving.
        }
        
        // Return false if x, y are connected.
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            
            // Make root of smaller rank point to root of larger rank.
            if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
            else {
                parent[rootX] = rootY;
                rank[rootX]++;
            }
            
            return true;
        }
    }


    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet disjointSet = new DisjointSet(edges.length);
        
        for (int[] edge : edges) {
            if (!disjointSet.union(edge[0] - 1, edge[1] - 1)) return edge;
        }
        
        throw new IllegalArgumentException();
    }


// ---------------------------------------------------

class UnionFind{
    private int[] id;
    UnionFind(int n){
        id = new int[n+1];
        for(int i = 0; i < id.length; i++) id[i] = i;
    }
    
    int find(int a){
        return id[a];
    }
    
    boolean connect(int u, int v){
        return find(u) == find(v);
    }
    
    void union(int u, int v){
        int uid = find(u);
        int vid = find(v);
        if(uid == vid) return;
        for(int i = 0; i < id.length; i++){
            if(id[i] == uid) id[i] = vid;
        }
    }
}


// Time & Space: O(N) O(N)  # nodes / edges

// Approach 1 : Union Find
public int[] findRedundantConnection(int[][] edges) {
    int N = edges.length;
    UF uf = new UF(N);
    for (int[] e : edges) {
        int u = e[0], v = e[1];
        if (uf.connect(u, v)) {
            return e;
        }
        uf.union(u, v);
    }
    return new int[]{-1, -1};
}


// Approach 2: DFS  Time O(N^2), Space O(N)

class Solution {
    Set<Integer> set = new HashSet(); 
    
    public int[] findRedundantConnection(int[][] edges) {
        // initialize graph as an array of arraylist 
        // for each node's neighbors
        ArrayList<Integer>[] graph = new ArrayList[1000+1];
        for(int i = 0; i <= 1000; i++){
            graph[i] = new ArrayList();
        }
        
        // for each edge, dfs two points (clear set)
        for(int[] ed : edges){
            set.clear();
            if(!graph[ed[0]].isEmpty() && !graph[ed[1]].isEmpty() && dfs(graph, ed[0], ed[1])){
                return ed;
            }
        // add neighbors for each point
            graph[ed[0]].add(ed[1]);
            graph[ed[1]].add(ed[0]);
            
        }
        throw new AssertionError();  
    }
    
    // return true if the find an redundant edge
    private boolean dfs(ArrayList<Integer>[] graph, int u, int v){
        if(!set.contains(u)){
            set.add(u);
            if(u == v) return true;
            // for each neighbor of source node
            for(int nei : graph[u]){
                // if neighbor is connect to target node
                if(dfs(graph, nei, v)) return true;
            }
        }
        return false;
    }
}





// https://leetcode.com/problems/redundant-connection-ii

/*
Return an edge that can be removed so that the resulting graph 
is a rooted tree of N nodes. 
If there are multiple answers, return the answer that occurs 
last in the given 2D-array.

Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3

Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3

*/


// Approach DFS Time & Space O(n)


class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        Map<Integer, Integer> parent = new HashMap();
        List<int[]> candidates = new ArrayList();
        for (int[] edge: edges) {
            if (parent.containsKey(edge[1])) {
                candidates.add(new int[]{parent.get(edge[1]), edge[1]});
                candidates.add(edge);
            } else {
                parent.put(edge[1], edge[0]);
            }
        }

        int root = orbit(1, parent).node;
        if (candidates.isEmpty()) {
            Set<Integer> cycle = orbit(root, parent).seen;
            int[] ans = new int[]{0, 0};
            for (int[] edge: edges) {
                if (cycle.contains(edge[0]) && cycle.contains(edge[1])) {
                    ans = edge;
                }
            }
            return ans;
        }

        Map<Integer, List<Integer>> children = new HashMap();
        for (int v: parent.keySet()) {
            int pv = parent.get(v);
            if (!children.containsKey(pv))
                children.put(pv, new ArrayList<Integer>());
            children.get(pv).add(v);
        }

        boolean[] seen = new boolean[N+1];
        seen[0] = true;
        Stack<Integer> stack = new Stack();
        stack.add(root);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!seen[node]) {
                seen[node] = true;
                if (children.containsKey(node)) {
                    for (int c: children.get(node))
                        stack.push(c);
                }
            }
        }
        for (boolean b: seen) if (!b)
            return candidates.get(0);
        return candidates.get(1);
    }

    public OrbitResult orbit(int node, Map<Integer, Integer> parent) {
        Set<Integer> seen = new HashSet();
        while (parent.containsKey(node) && !seen.contains(node)) {
            seen.add(node);
            node = parent.get(node);
        }
        return new OrbitResult(node, seen);
    }

}
class OrbitResult {
    int node;
    Set<Integer> seen;
    OrbitResult(int n, Set<Integer> s) {
        node = n;
        seen = s;
    }
}





// Approach: Union Find

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // check if there is a node have 2 parents, store in can1 can2
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] roots = new int[edges.length + 1];
        for(int[] edge: edges){
            int parent = edge[0];
            int child = edge[1];
            if(roots[child] == 0) roots[child] = parent;
            else{
                // the child already has a parent
                can2 = new int[]{parent, child}; // newer link
                can1 = new int[]{roots[child], child}; // older link
                edge[1] = 0; // set current link invalid ??
            }
        }
        // perform union find, if tree, return 2
        for(int i = 0; i < edges.length; i++){
            roots[i] = i;
        }
        
        for(int[] edge:edges){
            int parent = edge[0];
            int child = edge[1];
            if (child == 0) continue; // invalid link
            if(find(roots, parent) == child){
                // if no candicate = find a circle, return current edge
                if(can1[0] == -1) return edge;
                // else return 1 ???
                else return can1;
            }
            roots[child] = parent; // union    
        }
        return can2;
    }
    
    
    private int find(int[] roots, int id){
        while(id != roots[id]){
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }
}
