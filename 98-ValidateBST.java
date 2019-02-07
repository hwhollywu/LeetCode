// 98. Validate Binary Search Tree

// https://leetcode.com/problems/validate-binary-search-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Approach : Recursion
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    
    private boolean isValidBST(TreeNode root, Integer lower, Integer upper){
        if (root == null) return true;
        if (lower != null && root.val <= lower) return false;
        if (upper != null && root.val >= upper) return false;
        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }
}

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}

// Approach : Iterative
// Time O(N), Space O(N)

public boolean isValidBST(TreeNode root) {
   if (root == null) return true;
   Stack<TreeNode> stack = new Stack<>();
   TreeNode pre = null;
   while (root != null || !stack.isEmpty()) {
      while (root != null) {
         stack.push(root);
         root = root.left;
      }
      root = stack.pop();
      if(pre != null && root.val <= pre.val) return false;
      pre = root;
      root = root.right;
   }
   return true;
}


class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true; // base
        // DFS, store nodes and upper/lower limits for comparison
        LinkedList<TreeNode> stack = new LinkedList();
        LinkedList<Integer> upperlimits = new LinkedList();
        LinkedList<Integer> lowerlimits = new LinkedList();
        stack.add(root);
        upperlimits.add(null);
        lowerlimits.add(null);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.poll();
            Integer upper = upperlimits.poll();
            Integer lower = lowerlimits.poll();
            // check right
            if(node.right != null){
                if (node.right.val > node.val){
                    if (upper != null && upper <= node.right.val) return false;
                    stack.add(node.right);
                    upperlimits.add(upper);
                    lowerlimits.add(node.val);
                } else return false; // right smaller than current
            }
            // check left
            if(node.left != null){
                if(node.left.val < node.val){
                    if(lower != null && lower >= node.left.val) return false;
                    stack.add(node.left);
                    upperlimits.add(node.val);
                    lowerlimits.add(lower);
                }else return false;
            }        
        }
        return true;
    }
    
}