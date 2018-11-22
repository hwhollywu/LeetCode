// 24 Game

// https://leetcode.com/problems/24-game/


class Solution {
    public boolean judgePoint24(int[] nums) {
        // use list to store temp numbers
        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < nums.length; i++){
            list.add((double) nums[i]);
        }
        return judgePoint24Helper(list);
    }
    
    public boolean judgePoint24Helper(ArrayList<Double> nums){
        // base
        if (nums.size() == 0) return false;
        // check if the double remained is 24
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;
        for (int i = 0; i< nums.size(); i++){
            for (int j = 0; j < nums.size(); j++){
                if (i != j){
                    ArrayList<Double> nums2 = new ArrayList<Double>();
                    // if three different numbers, store k in nums2
                     for (int k = 0; k < nums.size(); k++){
                         if (k != i && k != j){
                             nums2.add(nums.get(k));
                         }
                     }

                    // 4 operations
                    for (int k = 0; k < 4; k++){
                        // for +, *, order doesn't matter
                        if (k < 2 && j > i) continue;
                        if (k == 0) nums2.add(nums.get(i) + nums.get(j));
                        if (k == 1) nums2.add(nums.get(i) * nums.get(j));
                        if (k == 2) nums2.add(nums.get(i) - nums.get(j));
                        if (k == 3) {
                            if (nums.get(j) != 0){
                                nums2.add(nums.get(i) / nums.get(j));
                            } else continue;
                        }

                        //System.out.println("size of nums2:" + nums2.size());
                        if (judgePoint24Helper(nums2)) return true;
                        // keep trying
                        nums2.remove(nums2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
    
}