494-TargetSum

// https://leetcode.com/problems/target-sum/


// Approach #1 Brute Force Time O(2^n), Space O(n)

class Solution {
    int res = 0;
    public int findTargetSumWays(int[] nums, int S) {
        findTargetSumWaysRec(nums,0,0,S);
        return res; 
    }
    
    private void findTargetSumWaysRec(int[] nums, int i, int sum, int S){
        if(i == nums.length){
            // deplete the num
            if(sum == S) res++;
        }else{
            findTargetSumWaysRec(nums, i+1, sum + nums[i], S);
            findTargetSumWaysRec(nums, i+1, sum - nums[i], S);
        }
    }
}


// Approach #2 DFS/Recursion with memoization Time O(l*n), Space O(n)
// l = range of sum

// calculate(nums, i, sum, S), store the result obtained in 
// memo[i][sum + 1000].  1000 use to offset` the sum value for mapping 
// Because the sum of elements in the given array will not exceed 1000.


public class Solution {
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for (int[] row: memo)
            Arrays.fill(row, Integer.MIN_VALUE);
        return calculate(nums, 0, 0, S, memo);
    }
    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S)
                return 1;
            else
                return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000];
            }
            int add = calculate(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + 1000] = add + subtract;
            return memo[i][sum + 1000];
        }
    }
}

// use 2D array memo[][] or hashmap to record the intermediate results

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 0) return 0;
        return findTargetSumWaysHelper(nums,0,0,S, new HashMap());
    }
    private int findTargetSumWaysHelper(int[] nums, int i, int sum, int S, HashMap<String,Integer> map){
        // i(0-20) + sum(0-1000) uniquely forms the key index 
        String key = i + "-" + sum;
        // check map 
        if (map.containsKey(key)){
            return map.get(key);
        }
        // check if target S is met
        if (i == nums.length){
            if (sum == S) return 1;
            else return 0;
        }
        // recursive case
        int add = findTargetSumWaysHelper(nums,i+1, sum + nums[i], S, map);
        int sub = findTargetSumWaysHelper(nums,i+1, sum - nums[i], S, map);
        map.put(key, add+sub);
        return add + sub;
    }
}


// Approach #3 DP

    public int findTargetSumWays(int[] nums, int S) {
        int totalSum = 0;
        for (int num : nums) {  //calculate the totalSum keeping all the elements in the array positive
            totalSum += num;  
        }
        if (totalSum < S || -totalSum > S) { //If the target sum S is not reachable by the range
            return 0;
        }
        int[] dp = new int[2 * totalSum + 1];
         //dp[i] -> the number of ways to have sum = i - totalSum
        dp[totalSum] = 1; 
        //We start from no elements in the array, so there is one way to have sum = 0 that there is no element
        for (int i = 0; i < nums.length; i++) { //Start from deciding to add the first element as positive or negative
            int[] next = new int[2 * totalSum + 1];
            for (int j = 0; j < 2 * totalSum + 1; j++) {
                if (dp[j] != 0) {  //if current sum j - totalSum is already reached by the previous searched numbers
                    next[j + nums[i]] += dp[j]; //plus the num of ways to have sum = j - totalSum to the num of ways to have sum = j + nums[i] - totalSum 
                    next[j - nums[i]] += dp[j];
                }//The previous reached range is  -totalSum < [-currSum, currSum ] < totalSum.
                //Since currSum + nums[i] no larger than totalSum, -currSum - nums[i] no smaller than -totalSum, so it won't exceed the boundary
            }
            dp = next;
        }
        return dp[S + totalSum]; //return the num of ways to have sum = S
    }
} 