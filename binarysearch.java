

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







