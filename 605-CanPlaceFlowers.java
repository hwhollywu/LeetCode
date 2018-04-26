// 605. Can Place Flowers
// https://leetcode.com/problems/can-place-flowers/description/

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int num_planted = 0;
        for (int i = 0; i < flowerbed.length; i++){
            // ignore 1s
            if (flowerbed[i] == 1) continue;
            // check before & after to see if can plant 
            int before = (i == 0 ? 0 : flowerbed[i - 1]);
            int after = (i == flowerbed.length - 1 ? 0: flowerbed[i + 1]);
            if (before == 0 && after == 0){
                num_planted++;
                flowerbed[i] = 1;
            }
        }
        return (num_planted >= n);
    }
}