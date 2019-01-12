99-RecoverBinarySearchTree

// https://leetcode.com/problems/recover-binary-search-tree/

/* 

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2

 Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3

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
    TreeNode pre = null; 
    TreeNode first = null; // node need to be swapped back
    TreeNode second = null;
    public void recoverTree(TreeNode root) {
        traverse(root);
        //swap
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    public void traverse(TreeNode node){
        if (node == null) return;
        traverse(node.left);
        // update first and second
        if (pre != null && pre.val >= node.val){
            // first always equal to the first found node
            if (first == null) first = pre;
            // update second = incorrect larger node
            second = node;
        }
        pre = node;
        traverse(node.right);
    }
}

// use Morris Traversal Space O(1) , no stack
// https://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html

public void recoverTree(TreeNode root) {
	//Morris-traversal
	
    TreeNode first = null;
    TreeNode second = null;
    
    TreeNode pred = null; //rightmost node in left tree
    TreeNode prev = null; 
    
    TreeNode curr = root;
    
    while(curr != null){
        //for each node, we compare it with prev node as we did in in-order-traversal
        if(prev != null && curr.val <= prev.val){
            if(first == null) first = prev;
            second = curr;
        }
        
        if(curr.left != null){
            //got left tree, then let's locate its rightmost node in left tree
            pred = curr.left;
            //we may have visited the left tree before, and connect the rightmost node with curr node (root node)
            while(pred.right != null && pred.right != curr){
                pred = pred.right;
            }
            
            if(pred.right == curr){
                //if this left tree has been visited before, then we are done with it
                //cut the connection with currNode and start visit curr's right tree
                pred.right = null;
                prev = curr;
                curr = curr.right;
            }else{
                //if this left tree has not been visited before, then we create a back edge from rightmost node
                // to curr node, so we can return to the start point after done the left tree
                pred.right = curr;
                curr = curr.left;
            }
            
        }else{
            //no left tree, then just visit its right tree
            prev = curr;
            curr = curr.right;
        }
    }
    
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
}

