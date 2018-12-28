94-BinaryTreeInorderTraversal

// https://leetcode.com/problems/binary-tree-inorder-traversal/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Approach 1: Recursive Approach Time: O(n) recursive Space: avg O(logN), worst O(n)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        inorderTraversalRec(root, res);
        return res;
    }
    
    private void inorderTraversalRec(TreeNode node, List<Integer> list){
        if (node == null) return;
        inorderTraversalRec(node.left, list);
        list.add(node.val);
        inorderTraversalRec(node.right, list);
    }
}

// Approach 2: Stack Time: O(n) Space: O(n)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
        	// Travel to each node's left child, till reach the left leaf
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            // backtrack to one higher level
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}



144-Preorder
//https://leetcode.com/problems/binary-tree-preorder-traversal/

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            if(curr == null) continue;
            res.add(curr.val);
            stack.push(curr.right); // LIFO right first in last out
            stack.push(curr.left);
        }
        return res;
    }
}

145-Postorder
// https://leetcode.com/problems/binary-tree-postorder-traversal/
// Preorder: root -> left -> right，
// Postorder: left -> right -> root
// Change Preoder to : root -> right -> left，
// use collections.reverse() , get the reversed order 


class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            if(curr == null) continue;
            res.add(curr.val);
            stack.push(curr.left); 
            stack.push(curr.right);
        }
        Collections.reverse(res);
        return res;
    }
        
}


// Iteration

class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> output = new LinkedList<>();
    if (root == null) {
      return output;
    }

    stack.add(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pollLast();
      output.addFirst(node.val);
      if (node.left != null) {
        stack.add(node.left);
      }
      if (node.right != null) {
        stack.add(node.right);
      }
    }
    return output;
  }
}
