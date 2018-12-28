133-CloneGraph
// https://leetcode.com/problems/clone-graph/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    // store the orig and clone node
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return node;
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node, clone);
        // for each neighbor
        for (UndirectedGraphNode neighbor : node.neighbors){
            if (!map.containsKey(neighbor)){
               cloneGraph(neighbor); // dfs neighbor
            }
            // get clone of each neighbor 
            UndirectedGraphNode neiclone = map.get(neighbor);
            clone.neighbors.add(neiclone);
        }
        return clone;
    }
}