// 349 Intersection of Two Arrays
// https://leetcode.com/problems/intersection-of-two-arrays/description/

// 区别：如果有intersection有重复element, 只要返回一个
// e.g. nums1 = [1,2,2,1], nums2 = [2,2] => [2]

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        // Time O(n+m) - depend on size of nums1/nums2
        if (nums1.length <= nums2.length){
            //search each ele in 1 in 2
            for (int i = 0; i < nums1.length ; i++){
                for (int j = 0; j < nums2.length ; j++){
                    if (nums1[i] == nums2[j]){
                        if (!list.contains(nums1[i])) list.add(nums1[i]);
                        break; 
                    }
                }
            }
        }else{
            // search each ele in 2 in 1
            for (int i = 0; i < nums2.length ; i++){
                for (int j = 0; j < nums1.length ; j++){
                    if (nums2[i] == nums1[j]){
                        if (!list.contains(nums2[i])) list.add(nums2[i]);
                        break; 
                    }
                }
            }
        }
        
        // convert arraylist to int[]
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
           array[i] = list.get(i);
        }
        return array;
        
    }
}

// 修改/加快：1. sorted array 2. add use hashset to test contain


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else{
                // find a intersection ele
                if (!set.contains(nums1[i])) list.add(nums1[i]);
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        // convert arraylist to int[]
        int[] array = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
           array[k] = list.get(k);
        }
        return array;
        
    }
}


// 350 https://leetcode.com/problems/intersection-of-two-arrays-ii/description/

// 区别：如果有intersection有重复element, 只要返回等同数量的element
// e.g. nums1 = [1,2,2,1], nums2 = [2,2] => [2,2]


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else{
                // find a intersection ele
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        // convert arraylist to int[]
        int[] array = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
           array[k] = list.get(k);
        }
        return array;
        
    }
}
