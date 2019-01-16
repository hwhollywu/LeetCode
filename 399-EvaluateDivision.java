399-Evaluate Division

// https://leetcode.com/problems/evaluate-division/

/*

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 

*/

/*
Time Complexity :  O(E + Q*(V + E)) where E is the number of edges, 
note that E is also the number of equations here.
1st, create the graph, i.e. construct the adjacency list, O(E) because we use the equations here
2nd, DFS for each query, DFS of graph is O(V + E), 
V: the number of nodes, E: the number of edges/equations, so total O(Q*(V + E))
So total O(E + Q*(V + E))
*/

// Approach DFS


class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // data structure used: 2 maps
        HashMap<String, ArrayList<String>> pairs = new HashMap(); // each node and its neighbors
        HashMap<String, ArrayList<Double>> valuepairs = new HashMap(); // each node and corresponding edges with its neighbors
        // 1. create graph from nodes of equations
        for(int i = 0; i < equations.length; i++){
            String[] equation = equations[i];
            // if new node, add node
            if(!pairs.containsKey(equation[0])){
                pairs.put(equation[0], new ArrayList<String>());
                valuepairs.put(equation[0], new ArrayList<Double>());
            }
            if(!pairs.containsKey(equation[1])){
                pairs.put(equation[1], new ArrayList<String>());
                valuepairs.put(equation[1], new ArrayList<Double>());
            }
            // add neighbor node and value (two-ways)
            pairs.get(equation[0]).add(equation[1]);
            valuepairs.get(equation[0]).add(values[i]);
            pairs.get(equation[1]).add(equation[0]);
            valuepairs.get(equation[1]).add(1/values[i]);
        }
        // 2. DFS each query, find each result
        double[] res = new double[queries.length];
        for(int i = 0; i < queries.length; i++){
            String[] query = queries[i];
            res[i] = dfs(query[0], query[1], pairs, valuepairs, new HashSet<String>(), 1.0);
            if(res[i] == 0.0) res[i] = -1.0;
        }
        return res;
    }
    
    // why hashset? - mark as visited ; why val? - backtracking, continue to find solution
    private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> valuepairs, HashSet<String> set, double val){
        // base
        if (set.contains(start)) return 0.0;
        if (!pairs.containsKey(start)) return 0.0;
        if (start.equals(end)) return val; // self pointing
        set.add(start); // mark as visited
        ArrayList<String> startlist = pairs.get(start);
        ArrayList<Double> startvaluelist = valuepairs.get(start);
        double res = 0.0;
        // for each neighbor node
        for(int i = 0; i < startlist.size(); i++){
            res = dfs(startlist.get(i), end, pairs, valuepairs, set, val*startvaluelist.get(i));
            if(res != 0.0) break;
        }
        set.remove(start); // mark as not visited
        return res;
    }
    
    
}

//  Approach Union Find
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, String> parent = new HashMap<>();  //<node, parent of the node>
        Map<String, Double> ratio = new HashMap<>();   //<node, node / parent>
        for (int i = 0; i < equations.length; i++) {
            union(parent, ratio, equations[i][0], equations[i][1], values[i]);
        }
        
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String s1 = queries[i][0], s2 = queries[i][1];
            if (!parent.containsKey(s1) || !parent.containsKey(s2)
                || !find(parent, ratio, s1).equals(find(parent, ratio, s2))) {
                res[i] = -1.0;
            } else {
                res[i] = ratio.get(s1) / ratio.get(s2);
            }
        }
        return res;
    }
    
    private void union(Map<String, String> parent, Map<String, Double> ratio, String s1, String s2, double val) {
            if (!parent.containsKey(s1)) {
                parent.put(s1, s1);
                ratio.put(s1, 1.0);
            }
            if (!parent.containsKey(s2)) {
                parent.put(s2, s2);
                ratio.put(s2, 1.0);
            }
            String p1 = find(parent, ratio, s1);
            String p2 = find(parent, ratio, s2);
            parent.put(p1, p2);
            ratio.put(p1, val * ratio.get(s2) / ratio.get(s1));
    }
    
    private String find(Map<String, String> parent, Map<String, Double> ratio, String s) {
        if (s.equals(parent.get(s))) {
            return s;
        }
        String father = parent.get(s);
        String grandpa = find(parent, ratio, father);
        parent.put(s, grandpa);
        ratio.put(s, ratio.get(s) * ratio.get(father));
        return grandpa;
    }
}

