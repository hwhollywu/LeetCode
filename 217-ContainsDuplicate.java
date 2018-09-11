
// 217. Contains Duplicate
// https://leetcode.com/problems/contains-duplicate/description/

// Brute Force -452ms O(n^2)

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

// Time O(nlogn), use Arrays.sort(); -7ms

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n-1; i++){
            if (nums[i] == nums[i+1]){
                return true;
            }
        }
        
        return false;
    }
}


// Using hashset -19ms
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>(nums.length);
        for(int n : nums){
        	if(set.contains(n)) return true;
        	set.add(n);
        }
        return false;
    }
}