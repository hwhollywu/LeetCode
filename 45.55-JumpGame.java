45.55-JumpGame

// https://leetcode.com/problems/jump-game/

/*

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
*/

// Complexity: Time O(n), Space O(1) 

public boolean canJump(int[] nums) {
    int max = 0;
    for(int i=0;i<nums.length;i++){
        if(i>max) {return false;} // max can't reach last index
        max = Math.max(nums[i]+i,max);
    }
    return true;
}


// Approach DP:
