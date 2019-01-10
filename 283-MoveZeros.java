// 282 Move Zeros
// https://leetcode.com/problems/move-zeroes/description/


// 100% solution


class Solution {
    public void moveZeroes(int[] nums) {
        int zeros = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++){
            if (nums[i] == 0){
                zeros++;
            }
        }
        // known zeros, n-zeros=numbers
        int count = 0;
        for (int i = 0; i < n; i++){
            if (count >= (n - zeros )) break;
            if (nums[i] != 0){
                nums[count] = nums[i];
                count++;
            }
        }
        // set the rest to zeros
        for (int i = n - zeros; i < n; i++){
            nums[i] = 0;
        }
    }
}