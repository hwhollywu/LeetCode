
// 198. House Robber
// https://leetcode.com/problems/house-robber/


/*

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

*/


/// USE DP
class Solution {
    public int rob(int[] nums) {
        // base cases
        if (nums.length < 1) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; 
        if (nums.length == 1) return dp[0]; 
        dp[1] = Math.max(nums[0],nums[1]); 
        for (int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }
}

// Space O（1）

class Solution {
    public int rob(int[] nums) {
        if (nums.length < 1) return 0;
        int pre2 = 0;
        int pre1 = 0;
        for (int i = 0; i < nums.length; i++){
            int cur = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1; // n-1
    }
}

// 213 
// https://leetcode.com/problems/house-robber-ii/

/*
Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/


// Circle ! first and last are neighbors

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        // base cases
        if (n < 1) return 0;
        if (n == 1) return nums[0];
        int[] rob1 = new int[n]; // rob first 
        int[] rob2 = new int[n]; // rob last
        rob1[0] = nums[0];
        rob1[1] = rob1[0]; // can't rob both first and second
        for (int i = 2; i < n-1; i++){ // restrict rob first to not rob last
            rob1[i] = Math.max(rob1[i-2]+nums[i], rob1[i-1]);
        }
        rob2[0] = 0; // restrict rob last to not rob first
        rob2[1] = nums[1];
        for (int i = 2; i < n; i++){
            rob2[i] = Math.max(rob2[i-2]+nums[i], rob2[i-1]);
        }
        System.out.println("rob1" + Arrays.toString(rob1));
        System.out.println("rob2" + Arrays.toString(rob2));
        return Math.max(rob1[n-2], rob2[n-1]); 
        // n-2 for rob1 because last element won't be initiated
    }
}

// use 198 as helper function

class Solution {
    public int rob(int[] nums) {
        // base cases
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        // compare rob first, and rob end
        return Math.max(robHelper(nums, 0, n-1), robHelper(nums, 1, n));
    }
    
    public int robHelper(int[] nums, int start, int end) {
        int pre2 = 0;
        int pre1 = 0;
        for (int i = start; i < end; i++){
            int cur = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}


// 337. House Robber III
// sum of not directly-linked Binary Tree!
// https://leetcode.com/problems/house-robber-iii/

/*
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

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

// recursion
class Solution {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        return Math.max(robInclude(root), robExclude(root));
    }
    
    
    public int robInclude(TreeNode root){
        if (root == null) return 0;
        return robExclude(root.left) + robExclude(root.right) + root.val; // add node val
    }
    
    public int robExclude(TreeNode root){
        if (root == null) return 0;
        return rob(root.left) + rob(root.right); // call back main function
    }
    
}


// post order traversal = DFS = Recursion, DP
// https://leetcode.com/problems/house-robber-iii/discuss/184212/Java-Post-Order-Traversal


class Solution {
        
    public int rob(TreeNode root) {
        int[] max = dfs(root);
        return Math.max(max[0], max[1]);
    }
    
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{ 0, 0 };
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int absentMax = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        int presentMax = left[0] + right[0] + node.val;
        return new int[]{ absentMax, presentMax };
    }
     
}

