450. Delete Node in a BST

//https://leetcode.com/problems/delete-node-in-a-bst/

/*

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

*/



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
    public TreeNode deleteNode(TreeNode root, int key) {        
        //base1
        if(root == null) return root;
        //recursive bases
        if(root.val < key) root.right = deleteNode(root.right, key);
        else if(root.val > key) root.left = deleteNode(root.left, key);
        else{
            // key found in root.val
            //if left empty
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            // if both children exist, find the minimum of right subtree
            TreeNode min = findMin(root.right);
            root.val = min.val;
            root.right = deleteNode(root.right, min.val);
        }
        return root;
    }
    public TreeNode findMin(TreeNode node){
        // traverse the left end
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    
}


