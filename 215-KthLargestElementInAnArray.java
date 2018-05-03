// 215. Kth Largest Element in an Array 
// https://leetcode.com/problems/kth-largest-element-in-an-array/description/


// Big O Cheatsheet: http://bigocheatsheet.com/

// default sorting algorithm
// Time O(NlogN), space O(1)

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

// the following sorting algorithm has time complexity of O(NlogN)

// 归并排序 merge sort 
// Time O(nlogn) Space O(n)

// 堆排序 heap sort
// space O(1)

// 快速排序 quick sort
// space O(logn)
