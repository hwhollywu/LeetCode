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


class Solution {
    public void moveZeroes(int[] nums) {

        int nonzeros = 0;
        // two pointers, one point to i's, the other point 
        // to non-zero nums and copy element from i
        for (int i = 0; i < nums.length; i++){
            // if(nonzeros > nums.length - zeros) break;
            if (nums[i] != 0){
                nums[nonzeros] = nums[i];
                nonzeros++;
            }
        }
        for(int i = nonzeros; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}