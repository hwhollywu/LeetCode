621-TaskScheduler
// https://leetcode.com/problems/task-scheduler/


/*
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
*/

// Sorting  Time O(n),  n = result time Space O(1)  // O(26) 
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // store number of tasks in a map (26 digits array)
        int[] map = new int[26];
        for (char c : tasks){
            map[c - 'A'] ++;
        }
        Arrays.sort(map); //sort by numbers, dont care about chars 
        int res = 0;
        while(map[25] > 0){ // there is still task in map
            int i = 0; // map index
            while(i <= n){ //
                if(map[25] == 0) break;
                //schedule a task, from most ones 
                //if no task on index, just being idle, not decreasing map
                if(i < 26 && map[25 - i] > 0) map[25 - i]--;
                res++;
                i++;
            }
            Arrays.sort(map); // resort after one task has been depleted
        }   
        return res;
        
    }
}

// Sorting  Time O(n),  n = result time Space O(1)  // O(26) 


class Solution {
    public int leastInterval(char[] tasks, int n) {
        // save chars into map
        int[] map = new int[26];
        for(char c :tasks)
            map[c - 'A']++;
        // add number into ordered queue
        PriorityQueue<Integer> pq = new PriorityQueue<>(26, Collections.reverseOrder()); // descending order 
        for (int i : map){
            if (i > 0) pq.add(i);
        }
        int res = 0;
        while(!pq.isEmpty()){
            int i = 0;
            // store non-zero items 
            List<Integer> temp = new ArrayList<>();
            while(i <= n){
                if(!pq.isEmpty()){
                    if(pq.peek() > 1) temp.add(pq.poll() - 1);
                    else pq.poll(); // idle
                }
                res++;
                if (pq.isEmpty() && temp.size() == 0) break;
                i++;
            }
            for(int l : temp) pq.add(l);
        }
        return res;
    }
}