475-Heaters

//https://leetcode.com/problems/heaters/

// Time complexity:  O(mlogn)
//  m is the length of houses, n is the length of heaters.

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        // base case
        if(houses == null || heaters == null) return 0;
        int res = Integer.MIN_VALUE; // later find the max value
        Arrays.sort(heaters);
        for (int i = 0; i < houses.length; i++){
            // for each house, find the radius to the closest heater
            int rad = binarySearch(heaters, houses[i]);
            res = Math.max(res, rad);
        }
        return res;
    }
    
    private int binarySearch(int[] heaters, int house){
        int l = 0;
        int r = heaters.length - 1;
        // compare distance from right & left to the house
        int dr = Integer.MAX_VALUE;
        int dl = Integer.MAX_VALUE;
        while(l <= r){
            int m = l + (r - l) / 2;
            if (heaters[m] == house) return 0;
            else if (heaters[m] > house){
                dr = heaters[m] - house;
                r = m - 1;
            }else{
                dl = house - heaters[m];
                l = m + 1;
            }
        }
        // l > r
        return Math.min(dr, dl);
    }
}