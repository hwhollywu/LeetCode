// 88. Merge Sorted Array
// https://leetcode.com/problems/merge-sorted-array/description/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // index traversal backwards so that won't cover the defined parts of nums1
        int i = m - 1, j = n - 1;
        int index = m + n - 1;
        while (i >= 0 || j >= 0){
            // nums1 is depleted
            if (i < 0) nums1[index] = nums2[j--];
            // nums2 is depleted
            else if (j < 0) nums1[index] = nums1[i--];
            else if (nums1[i] >= nums2[j]) nums1[index] = nums1[i--];
            else nums1[index] = nums2[j--];
            index--;
            }
        }
    }