
// 217. Contains Duplicate
// https://leetcode.com/problems/contains-duplicate/description/

// Brute Force
class Solution {
    public boolean containsDuplicate(int[] nums) {
        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] == nums[i]){
                    return true;
                }
            }
        }
        return false;
    }
}