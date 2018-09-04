// 324. Wiggle Sort II
// https://leetcode.com/problems/wiggle-sort-ii/description/

// brute-force
// index from 0 to length - 1 -> different from example 
// index from length - 1 to 0 -> nums1 must have more elements when n is odd 
// break nums1 & nums2 simply into (n/2+1) won't work because even case will also break unevenly
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] nums1;
        int[] nums2;
        if (n % 2 == 0){ // if n is even
            nums1 = Arrays.copyOfRange(nums, 0, n/2);
            nums2 = Arrays.copyOfRange(nums, n/2, n);
        } else {
            nums1 = Arrays.copyOfRange(nums, 0, (n+1)/2);
            nums2 = Arrays.copyOfRange(nums, (n+1)/2, n);
        }
        int x = nums1.length - 1; // index for nums1
        int y = nums2.length - 1; // index for nums2
        boolean is_nums1 = true;
        for (int i = 0; i < n; i++){
            if ((is_nums1 && (x >= 0)) || (y < 0)) {
                nums[i] = nums1[x];
                x--;
                is_nums1 = false; // switch
            }else{
                nums[i] = nums2[y];
                y--;
                is_nums1 = true;
            }
        }
    }
}