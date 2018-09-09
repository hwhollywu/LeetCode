// 189. Rotate Array
// https://leetcode.com/problems/rotate-array/


// 建立一个temp array,再循环一遍把原来的array替换掉
// space O(n)

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++){
            temp[( i + k ) % n] = nums[i];
        }
        for (int i = 0; i < n; i++){
            nums[i] = temp[i];
        }
    }
}

// space O(1)
/* 
1-First reverse the whole array i.e..... 0 to n-1. [7,6,5,4,3,2,1]
2-reverse array from index 0 to k-1.....[5,6,7,4,3,2,1]
3-reverse array from index k to n-1.....[5,6,7,1,2,3,4]
*/

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        // Case: k may > n
        k = k % n;
        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }
    
    public void reverse(int[] array, int start, int end){
        while(start < end){
            //swap start and end
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}