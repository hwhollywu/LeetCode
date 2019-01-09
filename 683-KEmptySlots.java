683-KEmptySlots

//https://leetcode.com/problems/k-empty-slots/

/*
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.

Input: 
flowers: [1,2,3]
k: 1
Output: -1

*/

// Approach: TreeSet, Time O(NlogN), space O(N)


class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int res = 0;
        TreeSet<Integer> t = new TreeSet();
        for (int i = 0; i < flowers.length; i++){
            // for each flower position
            res++;
            t.add(flowers[i]);
            // get bloomed greater position than the current one
            Integer higher = t.higher(flowers[i]); 
            Integer lower = t.lower(flowers[i]);
            if (higher != null && higher - flowers[i] == k + 1 || lower != null && flowers[i] - lower == k + 1){
                // if position between reach target k
                return res;
            }
        }
        return -1;
    }
}


// Approach: two pointers + DP? + understanding  Time O(N), Space O(N)

class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int res = Integer.MAX_VALUE;
        // transform to days array
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++){
            days[flowers[i] - 1] = i + 1; // #days 
        }
        // to find a subarray of day[left, right] 
        // such that for all i's between left and right, 
        //days[i] > days[left] and days[i] > days[right] 
        // days i happen after 
        int l = 0;
        int r = k + 1;
        // search for l-r range
        for (int i = 0; r < days.length; i++){
        	// if between k flowers, there are earlier days, update res
            if(days[i] < days[l] || days[i] <= days[r]){
                if (i == r){
                    // find subarray, update res = minimum days
                    res = Math.min(res, Math.max(days[l], days[r]));
                }
                // increment left and right
                l = i;
                r = i + k + 1;
            }
            // else update i until i reach r
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}



