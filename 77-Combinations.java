77-Combinations

// https://leetcode.com/problems/combinations/description/

/*
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> temp = new ArrayList();
        combineRec(temp,res,1,n,k);
        return res;
    }
    private void combineRec(List<Integer> temp, List<List<Integer>> res, int start, int n, int k){
        // base case, return
        if (k == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        // recursive case
        // for (int i = start; i <= n; i++) also work, but n-k+1 add pruning
        for (int i = start; i <= n-k+1; i++){ //??? 
            temp.add(i);
            combineRec(temp, res, i+1, n, k-1);
            temp.remove(temp.size()-1);
        }
    }
}