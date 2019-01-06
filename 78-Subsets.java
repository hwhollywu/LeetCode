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



