78-Subsets

// https://leetcode.com/problems/subsets/description/


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        Arrays.sort(nums);
        subsetsRec(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    private void subsetsRec(List<List<Integer>> lists, List<Integer> temp, int[] nums, int start){
        lists.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++){
            temp.add(nums[i]);
            subsetsRec(lists, temp, nums, i+1);
            temp.remove(temp.size()-1);
        }
    }
    
}

// 2ms answer

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        //Arrays.sort(nums);
        for(int n : nums) {
            List<List<Integer>> tmp = new ArrayList<>();
            for(List<Integer> sub : res) {
                List<Integer> a = new ArrayList<>(sub);
                a.add(n);
                tmp.add(a);
            }
            res.addAll(tmp);
        }
        return res;
    }
}


90-Subsets II
// https://leetcode.com/problems/subsets-ii/description/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        subsetsWithDupRec(res, new ArrayList<>(), nums, visited, 0);
        return res;
    }
    
    private void subsetsWithDupRec(List<List<Integer>> lists, List<Integer> temp, int[] nums, boolean[] visited, int start){
        lists.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++){
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            temp.add(nums[i]);
            visited[i] = true;
            subsetsWithDupRec(lists, temp, nums, visited, i+1);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }
}

