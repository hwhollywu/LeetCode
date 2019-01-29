

// Template:
public int binarySearch(int[] nums, int key) {
    int l = 0, h = nums.length - 1;
    while (l <= h) {
        int m = l + (h - l) / 2;
        if (nums[m] == key) {
            return m;
        } else if (nums[m] > key) {
            h = m - 1;
        } else {
            l = m + 1;
        }
    }
    return -1;
}

// 在一个有重复元素的数组中查找 key 的最左位置
public int binarySearch(int[] nums, int key) {
    int l = 0, h = nums.length - 1;
    while (l < h) {
        int m = l + (h - l) / 2;
        if (nums[m] >= key) {
            h = m;
        } else {
            l = m + 1;
        }
    }
    return l;
}


69. Sqrt(x)
// https://leetcode.com/problems/sqrtx/

/*
Input: 4
Output: 2

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/


class Solution {
    public int mySqrt(int x) {
        // base
        if (x <= 1) return x;
        int l = 1; 
        int r = x-1;
        while (l <= r){  // ending has to be l > r
            int m = l + (r - l) / 2;
            if ((x/m) == m) return m;
            else if ((x/m) < m){
                r = m - 1;
            }else l = m + 1;
        }
        // l > r, return smaller one
        return r;
    }
}


744. Find Smallest Letter Greater Than Target

// https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
/*
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"
*/

// Linear time  O(n)
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        for (char ch : letters){
            if (ch > target) return ch;
        }
        return letters[0];
    }
}
// Binary Search O(logn)
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int r = letters.length - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (letters[m] <= target){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        // if l exceed limit, return first, else return l-th element
        return l >= letters.length ? letters[0] : letters[l] ; 
    }
}


540. Single Element in a Sorted Array
// https://leetcode.com/problems/single-element-in-a-sorted-array/description/

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r){
            int m = l + (r - l) / 2;
            if (m % 2 == 1) m -= 1; // make m always even
            if (nums[m] == nums[m+1]){
                // target appears after
                l = m + 2;
            }else{
                // target appears in or after 
                r = m;
            }
        }
        return nums[l];
    }
}


278. First Bad Version
// https://leetcode.com/problems/first-bad-version/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        while (l < r){
            int m = l + (r - l) / 2;
            if (isBadVersion(m)){
                // target appear before or at m
                r = m;
            }else{
                l = m + 1;
            }
        }
        return l;
    }
}


153. Find Minimum in Rotated Sorted Array
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r){
            int m = l + (r - l) / 2;
            if (nums[m] <= nums[r]){
                r = m;
            }else {
                l = m + 1;
            }
        }
        return nums[l];
    }
}



34. Find First and Last Position of Element in Sorted Array
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

/*
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target);
        int last = binarySearch(nums, target + 1) - 1;
        // not found case:
        if (first == nums.length || nums[first] != target){
            return new int[]{-1,-1};
        }
        return new int[]{first, last};
    }
    
    private int binarySearch(int[] nums, int target){
        int l = 0;
        int r = nums.length;
        while(l < r){
            int m = l + (r - l) / 2;
            if (nums[m] >= target){
                r = m;
            }else{
                l = m + 1;
            }
        }
        return l; // l>=r
    }
}


162-FindPeakElement

class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) r = mid; // inclusive
            else l = mid + 1;
        }
        // when l > r
        return l;
    }
}
