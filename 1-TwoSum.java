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
// traversal hash map twice 

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // map stores key (target minus n) & value (index of number n) 
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++){
            map.put(target - numbers[i], i + 1);
        }
        for (int i = 0; i < numbers.length; i++){
            if (map.get(numbers[i]) != null){
                // find the result
                int result1 = (i + 1);
                int result2 = map.get(numbers[i]);
                if (result1 > result2){
                    return new int[] {result2, result1};
                }else{
                    return new int[] {result1, result2};
                }
            }
        }
        return null;
    }
        
}

// Time O(n), Space O(n)
// Traversal once

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

// 167. Two Sum II - Input array is sorted
// instead of Hashmap, use two pointers point to start & end of the array
// Runtime: -> 1ms

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] result = new int[2];
        if (numbers == null || numbers.length < 2) return result;
        while (left < right){
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            }else if (sum > target){
                right--;
            }else {
                left ++;
            }
        }
        return result; 
    }
}


