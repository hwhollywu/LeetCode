162-FindPeakElement

// https://leetcode.com/problems/find-peak-element/

/*
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
*/


// Linear  Time O(N), Space O(1)
class Solution {
    public int findPeakElement(int[] nums) {
        for(int i = 0; i< nums.length - 1; i++){
            // only check right neighbor for [2,1]
            if(nums[i] > nums[i+1]) return i;
        }
        return nums.length - 1;  
    }
}


 // Approach Binary Seach O(logN), Space O(1)

class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) r = mid; // inclusive
            else l = mid + 1;
        }
        // when l > r
        return l;
    }
}
