// 561. Array Partition 1

class Solution {
    public int arrayPairSum(int[] nums) {
        //sort array, find the closest pair
        Arrays.sort(nums);
        int pairsum = 0;
        for (int i = 0; i< nums.length; i=i+2){
            pairsum += nums[i];
        }
        return pairsum;
        
    }
}