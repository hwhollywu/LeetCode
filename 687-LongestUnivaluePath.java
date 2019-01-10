687-LongestUnivaluePath

// https://leetcode.com/problems/longest-univalue-path/

// Complexity: Time O(N) N = # nodes, Space O(H) H=height of tree

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
    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathRec(root);
        return res;
    }
    
    private int longestUnivaluePathRec(TreeNode node){
        // base 
        if(node == null) return 0;
        int left = longestUnivaluePathRec(node.left);
        int right = longestUnivaluePathRec(node.right);
        // case left
        int arrowleft = 0, arrowright = 0;
        if (node.left != null && node.left.val == node.val){
            arrowleft = left + 1;
        }
        // case right
        if (node.right != null && node.right.val == node.val){
            arrowright = right + 1;
        }
        res = Math.max(res, arrowleft + arrowright);
        return Math.max(arrowleft,arrowright);
    }
    
}