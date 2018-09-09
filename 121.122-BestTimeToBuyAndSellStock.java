// 122. Best Time to Buy and Sell Stock II

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/


class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i< prices.length-1; i++){
            if (prices[i] < prices[i+1]){
                profit += (prices[i+1] - prices[i]);
            }
        }        
        return profit;
    }
}


// 121. Best Time to Buy and Sell Stock

// difference: only one transaction

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

class Solution {
    public int maxProfit(int[] prices) {
        // base case:
        if (prices.length < 1){
            return 0;
        }
        int min = prices[0];
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++){
            if (prices[i] < prices[i+1]){
                // calculate profit
                if ((prices[i+1] - min) > profit){
                    profit = prices[i+1] - min;
                }
            }else {
                if (prices[i+1] < min){
                    min = prices[i+1];
                }
            }
        }
        return profit;
    }
}