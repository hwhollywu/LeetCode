733-FloodFill
// https://leetcode.com/problems/flood-fill/

class Solution {
    private int nrow = 0;
    private int ncol = 0;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        nrow = image.length;
        ncol = image[0].length;
        // start from sr, sc, do dfs
        if (image[sr][sc] != newColor) dfs(image, sr, sc, newColor);
        return image;
    }
    
    private void dfs(int[][] image, int r, int c, int newColor){
        int oldcolor = image[r][c];
        image[r][c] = newColor;
        if (r-1 >=0 && image[r-1][c] == oldcolor){
            dfs(image, r-1, c, newColor);
        }
        if (r+1 < nrow && image[r+1][c] == oldcolor){
            dfs(image, r+1, c, newColor);
        }
        if (c-1 >= 0 && image[r][c-1] == oldcolor){
            dfs(image, r, c-1, newColor);
        }
        if (c+1 <ncol && image[r][c+1] == oldcolor){
            dfs(image, r, c+1, newColor);
        }
    }
}