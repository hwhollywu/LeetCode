// 665. Non-decreasing Array
// https://leetcode.com/problems/non-decreasing-array/description/

class Solution {
    public boolean checkPossibility(int[] nums) {
        int smaller = 0;
        for (int i = 0; i < nums.length-1; i++){
            if (nums[i+1] < nums[i]){
                smaller ++;
                // modify the element to make sure that 
                // later operation will not create more non-decreasing elements
                if (i - 1 >= 0 && nums[i-1] > nums[i+1]) {
                    nums[i+1] = nums[i]; // increase nums[i+1] 3 4(i) 2 -> 3 4 4 instead of 3 2 2
                } else nums[i] = nums[i+1]; // decrease nums[i] 1 4(i) 2 -> 1 4 4 
            }
            
        }
        return (smaller <= 1);
    }
}