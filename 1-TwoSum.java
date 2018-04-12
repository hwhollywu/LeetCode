// 1. Two Sum
// Time O(n^2), Space O(1)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        for (int i = 0 ; i < length; i++){
            for (int j = i+1; j < length; j++){
                if (nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}


// Time O(n), Space O(n)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // map stores key - value : the other value - the index of the value
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++){
            int other = target - nums[i];
            if (map.get(other) != null){
                return new int[] {map.get(other), i};
            }else map.put(nums[i], i);
        }
        return null;
    }
}

