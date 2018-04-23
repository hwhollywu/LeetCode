// 540. Single Element in a Sorted Array

// O(log n) time and O(1) space.

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int i = 0, length = nums.length - 1;
        while (i < length){
            if (nums[i] == nums[i+1]){
                i += 2;
            }else return nums[i];
        }
        return nums[i];
    }
}

// typical binary search 

public int singleNonDuplicate(int[] nums) {
    int l = 0, h = nums.length - 1;
    while(l < h) {
        int m = l + (h - l) / 2;
        // make sure m starts on an even number index
        if(m % 2 == 1) m--; 
        // continue searching from m+2 till h.
        if(nums[m] == nums[m + 1]) l = m + 2;
        //continue searching from l to m.
        else h = m;
    }
    return nums[l];
}