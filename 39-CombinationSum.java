39-CombinationSum

// https://leetcode.com/problems/combination-sum/description/

/*

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(candidates);
        combinationSumRec(new ArrayList(), res, candidates, target, 0);
        return res;
    }
    
    private void combinationSumRec(List<Integer> temp, List<List<Integer>> lists, int[] candidates, int target, int start){
        if (target < 0) return;
        else if (target == 0){
            lists.add(new ArrayList<>(temp));
            return;
        }else{
            for (int i = start; i < candidates.length; i++){
                if (candidates[i] <= target){
                    temp.add(candidates[i]);
                    combinationSumRec(temp, lists, candidates, target - candidates[i], i);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }
}

40. Combination Sum II
// https://leetcode.com/problems/combination-sum-ii/description/

// Each number in candidates may only be used once in the combination.
/* 

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

*/


class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length];
        combinationSum2Rec(new ArrayList(), res, candidates, visited, target, 0);
        return res;
    }
    
    private void combinationSum2Rec(List<Integer> temp, List<List<Integer>> res, int[] candidates, boolean[] visited, int target, int start){
        // base case
        if (target < 0) return;
        else if (target == 0){
            // add to res list
            res.add(new ArrayList<>(temp));
            return;
        }else{
            // recursive case
            for (int i = start; i < candidates.length; i++){
            	// or not use visited: if (i > start && candidates[i] == candidates[i-1]) continue;
                if (i > 0 && candidates[i] == candidates[i-1] && !visited[i-1]) continue; // ??
                if (candidates[i] <= target){
                    visited[i] = true;
                    temp.add(candidates[i]);
                    combinationSum2Rec(temp, res, candidates, visited, target - candidates[i], i + 1); // ??  non-repeat element
                    temp.remove(temp.size()-1);
                    visited[i] = false;
                }
            }
            
        }
    }
}


216. Combination Sum III
// https://leetcode.com/problems/combination-sum-iii/description/

/*
Input: k = 3, n = 7
Output: [[1,2,4]]
*/

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList();
        combinationSum3Rec(new ArrayList(), res, k, n, 1);
        return res;
    }
    
    private void combinationSum3Rec(List<Integer> temp, List<List<Integer>> lists, int k, int n, int start){
        // base case
        if (n == 0 && temp.size() == k){
            lists.add(new ArrayList<>(temp));
            return;
        }else{
            for (int i = start; i <= 9 ; i++){
                temp.add(i);
                combinationSum3Rec(temp, lists, k, n-i, i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
}


