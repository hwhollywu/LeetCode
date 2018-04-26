// 452. Minimum Number of Arrows to Burst Balloons
// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/


// beat 24%

class Solution {
    public int findMinArrowShots(int[][] points) {
        // base case : when there is no balloon
        if (points.length == 0) return 0;
        // sort the balloons by end points (lambda expression as comparator)
        Arrays.sort(points,(a,b) -> (a[1] - b[1]));
        // start with the end of the first balloon to make sure the leftest bursts 
        // and shot as much as (following) balloons as possible
        int arrow_shot = points[0][1]; 
        int num_arrow = 1;
        for (int i = 1; i< points.length; i++){
            // burst the balloon as long as the arrow is higher than the start point 
            if (points[i][0] <= arrow_shot){
                // burst the balloon
                continue;
            }
            arrow_shot = points[i][1];
            num_arrow ++;
        }
        return num_arrow;
    }
}