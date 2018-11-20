// 107. Binary Tree Level Order Traversal II

// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/



// 102. Binary Tree Level Order Traversal
// https://leetcode.com/problems/binary-tree-level-order-traversal/

class Solution {
    
        public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        levelOrderHelper(root,1,result);
        // Collection.reverse(result);
        return result;
    }
    
    public void levelOrderHelper(TreeNode n, int level, List<List<Integer>> list){
        if (n == null) return;
        
        // compare level and result list size to decide if it's a new level
        if (level > list.size()){
            List curr = new ArrayList<>();
            curr.add(n.val);
            list.add(curr);
        }else {
        	// arraylist indexing starting from 0
            list.get(level-1).add(n.val);
        }
                
        levelOrderHelper(n.left, level+1,list);
        levelOrderHelper(n.right, level+1,list);
    }
}