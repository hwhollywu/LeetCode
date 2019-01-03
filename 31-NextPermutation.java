31-NextPermutation

// https://leetcode.com/problems/next-permutation/

// swap numbers:  Time complexity : O(n) Space: O(1) - constraint

class Solution {
    public void nextPermutation(int[] nums) {
        // from right to left, find the first smaller/descending element 
        int i = nums.length - 2;
        while(i >= 0 && nums[i+1] <= nums[i]) i--;
        // if reach the left end, all greater
        if (i < 0) {
            reverse(nums, 0);
            return;
        }
        // find the number (just greater than i) to be swapped with
        int j = nums.length -1;
        while(j >=0 && nums[j] <= nums[i]) j--;
        swap(nums, i, j);
        reverse(nums, i+1);
    }
    
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int start){
        // use two pointers to do the swap
        int i = start;
        int j = nums.length - 1;
        while(i < j){
            swap(nums, i ,j);
            i++;
            j--;
        }
    }
}
