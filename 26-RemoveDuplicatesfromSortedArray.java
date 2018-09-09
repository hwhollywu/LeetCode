// 26. Remove Duplicates from Sorted Array


class Solution {
    public int removeDuplicates(int[] nums) {
        int result = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] != nums[i-1]){
                result++;
                nums[result] = nums[i];
            }
        }
        return result+1;
    }
}

