46-Permutations
// https://leetcode.com/problems/permutations/description/

/* 
distinct numbers 

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> p = new ArrayList();
        boolean[] visited = new boolean[nums.length];
        permuteRec(p, res, visited, nums);
        return res;
    }
    
    private void permuteRec(List<Integer> p, List<List<Integer>> permutes, boolean[] visited, int[] nums){
        // base case - return
        if (p.size() == nums.length){
            permutes.add(new ArrayList<>(p)); // new arraylist! 
            return;
        }
        // recursive case 
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            p.add(nums[i]);
            permuteRec(p, permutes, visited, nums);
            p.remove(p.size() - 1);
            visited[i] = false;
        }
    }
    
}

// https://leetcode.com/problems/permutations-ii/

/*
duplicated numbers 

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> temp = new ArrayList();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUniqueRec(temp, res, visited, nums);
        return res;
    }
    
    private void permuteUniqueRec(List<Integer> temp, List<List<Integer>> lists, boolean[] visited, int[] nums){
        if (temp.size() == nums.length){
            lists.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (visited[i]) continue;
            else if (i >= 1 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            else{
                visited[i] = true;
                temp.add(nums[i]);
                permuteUniqueRec(temp, lists, visited, nums);
                temp.remove(temp.size() - 1);
                visited[i] = false;
            }
        }
    }
}

