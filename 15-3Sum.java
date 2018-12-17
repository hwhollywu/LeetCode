
// https://leetcode.com/problems/3sum/


// use 2sum -> wrong answer

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++){
            HashMap<Integer, Integer> map = new HashMap<>();
            int other = 0 - nums[i];
            for (int j = i+1; j < nums.length; j++){
                other -= nums[j];
                if (map.get(other) != null){
                     List<Integer> one = new ArrayList<>();
                    one.add(nums[i]);
                    one.add(nums[j]);
                    one.add(nums[map.get(other)]);
                    res.add(one);
                }else map.put(nums[j],j);
            }
        }
        return res;
        
    }



// use two pointers for two sum


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums); // have to sort
        for (int i = 0; i < nums.length - 2; i++){ // remaining 2 elements
            // skip same i
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            // two pointers
            int l = i+1;
            int r = nums.length-1;
            int target = 0 - nums[i];
            while (l < r){
                if (nums[l] + nums[r] == target){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++; // skip same l
                    while (l < r && nums[r] == nums[r-1]) r--; // skip same r
                    l++;
                    r--;
                } else if (nums[l]+nums[r] < target) l++;
                else r--;
            }
            
        }
        return res;
        
    }
}

