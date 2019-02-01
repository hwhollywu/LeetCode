697. Degree of an Array

// https://leetcode.com/problems/degree-of-an-array/


// Approach: hashmap, queue Time O(mn) m = # highest degree elements Space O(n + m)

class Solution {
    public int findShortestSubArray(int[] nums) {
        // map store each element and its degree
        Map<Integer, Integer> map = new HashMap<>();
        // first traversal finds the element with highest degree (maybe multiple)
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int maxfreq = Integer.MIN_VALUE;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            Integer value = entry.getValue();
            if (value > maxfreq) maxfreq = value;
        }
        Queue<Integer> elements = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == maxfreq)  elements.add(entry.getKey());
        }
        // second traversal to record the length of subarray 
        int min = Integer.MAX_VALUE;
        // for each of the element with highest freq
        while(!elements.isEmpty()){
            int ele = elements.poll();
            int freq = 0;
            int start = 0;
            for(int i = 0; i < nums.length; i++){
                //System.out.println("ith: " + nums[i] + " ele" + ele + " freq " + freq +" maxfreq" + maxfreq);
                if(nums[i] != ele){
                    if (start == i) start += 1;
                }else{
                    freq += 1;
                    if (freq == maxfreq){
                        if(i - start + 1 < min) min = i - start + 1;
                        break;
                    }
                }
            }
        }
        return min;
        
    }
}


// Approach: Time O(n)  Space O(n)

class Solution {
    public int findShortestSubArray(int[] nums) {
        // map store each element's first and last occurence in array
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int x = nums[i];
            // update each map
            if (left.get(x) == null) left.put(x, i); // only update once
            right.put(x, i); // always update
            count.put(x, count.getOrDefault(x, 0) + 1);
        }
        
        int res = nums.length; // initialize as max length
        int degree = Collections.max(count.values()); // get max value of hashmap
        for(int x: count.keySet()){ 
            if(count.get(x) == degree){ // if max degree
                res = Math.min(res, right.get(x) - left.get(x) + 1);
            }
        }
        return res;
    }
}
