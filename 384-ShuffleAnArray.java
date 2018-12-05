// 384. Shuffle an Array

// https://leetcode.com/problems/shuffle-an-array/

// https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
// https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
// an algorithm for generating a random permutation of a finite sequence


class Solution {
    
    private int[] array;
    private int[] orig;
    Random rand = new Random();

    public Solution(int[] nums) {
        array = nums;
        orig = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = orig.clone();
        return orig;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = array.length;
        for (int i=0;i<n;i++){
            int j = rand.nextInt(n-i)+i; // i-n range random index
            // swap num at i and j
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */