257-BinaryTreePaths

// https://leetcode.com/problems/binary-tree-paths/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// Approach Recursion: O(N)

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList();
        binaryTreePathsRec(root, "", res);
        return res;
    }
    
    private void binaryTreePathsRec(TreeNode node, String path, List<String> list){
        if (node == null) return;
        path += Integer.toString(node.val);
        if (node.left == null && node.right == null){ // if leaf
            list.add(path);
        }else{
            path += "->";
            binaryTreePathsRec(node.left, path, list);
            binaryTreePathsRec(node.right, path, list);
        }
    }
}

// Approach Iteration: O(N)


class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
    LinkedList<String> paths = new LinkedList();
    if (root == null)
      return paths;

    LinkedList<TreeNode> node_stack = new LinkedList();
    LinkedList<String> path_stack = new LinkedList();
    node_stack.add(root);
    path_stack.add(Integer.toString(root.val));
    TreeNode node;
    String path;
    while ( !node_stack.isEmpty() ) {
      node = node_stack.pollLast();
      path = path_stack.pollLast();
      if ((node.left == null) && (node.right == null))
        paths.add(path);
      if (node.left != null) {
        node_stack.add(node.left);
        path_stack.add(path + "->" + Integer.toString(node.left.val));
      }
      if (node.right != null) {
        node_stack.add(node.right);
        path_stack.add(path + "->" + Integer.toString(node.right.val));
      }
    }
    return paths;
  }
}

