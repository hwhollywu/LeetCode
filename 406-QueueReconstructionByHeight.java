// 406. Queue Reconstruction by Height
// https://leetcode.com/problems/queue-reconstruction-by-height/description/

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // base case
        if(people.length == 0) return new int[0][0];
        // sort by descending height ([0]) and ascending order([1])
        // must be descending because need to insert tallest first 
        // the last is the shortest: the index will be definite (won't change)
        Arrays.sort(people, (a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1];
                return b[0] - a[0];
            });
        int n = people.length;
        List<int[]> temp = new ArrayList<>();
        // traversal 
        for (int[] p : people) {
            temp.add(p[1], p);
        }
        return temp.toArray(new int[n][2]);
    }
}