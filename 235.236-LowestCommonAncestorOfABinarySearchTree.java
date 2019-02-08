235. Lowest Common Ancestor of a Binary Search Tree

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

/* 
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
*/


// Recursive Time & Space O(N)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // if(root == null || p == null || q == null) return null;
        
        // compare value of root, p, q to decide which subtree to search on
        if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }else if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }else{ // base case 
            return root;
        }
    }
}

// Iterative Time O(N), Space O(1)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root; // index node
        while(node != null){
            // compare value of root, p, q to decide which subtree to search on
            if(p.val > node.val && q.val > node.val){
                return lowestCommonAncestor(node.right, p, q);
            }else if(p.val < node.val && q.val < node.val){
                return lowestCommonAncestor(node.left, p, q);
            }else{ // base case 
                return node;
            }
        }
        return null;
    }
}


236. Lowest Common Ancestor of a Binary Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

// https://leetcode.com/articles/lowest-common-ancestor-of-a-binary-tree/


// Recursive Approach Time & Space O(N)

class Solution {
    
    private TreeNode node = null;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // traverse tree, mark found node as true, backtrack and return node
        lowestCommonAncestorRec(root, p, q);
        return node;
        
    }
    
    private boolean lowestCommonAncestorRec(TreeNode cur, TreeNode p, TreeNode q){
        // base
        if (cur == null) return false;
        // recursive, record if found nodes in left/right subtree or current node
        int left = lowestCommonAncestorRec(cur.left, p, q) ? 1 : 0;
        int right = lowestCommonAncestorRec(cur.right, p, q) ? 1 : 0; 
        int mid = (cur == p || cur == q) ? 1 : 0;
        // possible cases: both left, both right, one left, one mid ; one right, one mid
        if(mid + right + left >= 2){
            node = cur; // ancester is the current node, keep updating
        }
        // return true if any one of the three is valid 
        // false if no target node exist in this subtree
        return (mid + right + left > 0);
    }
}


// Iterative  Time & Space O(N)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        // use map to store parent node,  traverse tree
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        stack.push(root);
        // stop if both nodes are found
        while(!map.containsKey(p) || !map.containsKey(q)){
            TreeNode node = stack.pop();
            // save to map and stack
            if(node.left != null){
                map.put(node.left, node);
                stack.push(node.left);
            }
            if(node.right != null){
                map.put(node.right, node);
                stack.push(node.right);
            }
        }
        // find ancester of p and q
        // store all the ancester nodes of p (and q)
        Set<TreeNode> ancesters = new HashSet<>();
        while(p != null){
            ancesters.add(p);
            p = map.get(p); // get parent 
        }
        while(!ancesters.contains(q)){
            q = map.get(q);
        }
        // find common ancester
        return q;
    }
}




