671-SecondMinimumNodeInABinaryTree

// https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/

/*
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.

Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

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
    int min = Integer.MAX_VALUE;
    int min2 = Integer.MAX_VALUE;
    
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        min = root.val;
        findSecondMinimumValueHelper(root);
        return (min2 == Integer.MAX_VALUE) ? -1 : min2;
    }
    private void findSecondMinimumValueHelper(TreeNode node){
        if (node == null) return;
        if (node.val < min){
            min = node.val;
            min2 = min;
        }else if (node.val > min && node.val < min2){
            min2 = node.val;
        }
        findSecondMinimumValueHelper(node.left);
        findSecondMinimumValueHelper(node.right);
    }
}