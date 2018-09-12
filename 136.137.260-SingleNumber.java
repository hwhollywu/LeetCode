// 136 Single Number
// https://leetcode.com/problems/single-number/description/

// all number appear twice in an array except for one number
// note: no additional space needed

class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length - 2; i += 2){
            if (nums[i] != nums[i+1]){
                return nums[i];
            }
        }
        // else: the last element,because it's not examined in the loop
        return nums[nums.length - 1];
    }
}

// 137
// https://leetcode.com/problems/single-number-ii/description/

// all number appear three times in an array except for one number

class Solution {
    public int singleNumber(int[] nums) {
    	int len = nums.length;
        Arrays.sort(nums);
        // add case
        if (len == 1 || nums[1] != nums[0]) return nums[0];
        for (int i = 0 ; i < len - 1; i += 3){
            if (nums[i] != nums[i+1]) return nums[i];
        }
        // else: the last element,because it's not examined in the loop
        return nums[nums.length - 1];
    }
}



// 260
// https://leetcode.com/problems/single-number-iii/description/

// exactly two elements appear only once and all the other elements appear exactly twice.
// return these two elements

// Time O(n), space O(1)

class Solution {
    public int[] singleNumber(int[] nums) {
        int num1 = -1;
        int num2 = -1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] == nums[i+1]){
                // a pair
                i++;
            } else {
                if (num1 == -1){
                    num1 = nums[i];
                }else{
                    num2 = nums[i];
                    return new int[]{num1,num2};
                }
            }
        }
        num2 = nums[nums.length - 1];
        return new int[]{num1,num2};
    }
}




