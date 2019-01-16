//53. Maximum Subarray

// https://leetcode.com/problems/maximum-subarray/

/*
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            // conditionally update temp, result
            if (temp[i-1] + nums[i] > nums[i]){
                temp[i] = temp[i-1] + nums[i];
            }else {
                temp[i] = nums[i];
            }
            result = Math.max(temp[i], result);
        }
        System.out.println(Arrays.toString(temp));
        return result;
        
    }
}