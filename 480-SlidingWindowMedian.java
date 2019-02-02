480. Sliding Window Median

// https://leetcode.com/problems/sliding-window-median/

/*

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6

*/

// Brute Force 	Time  O(n k log(k))  Space O(k) for window 
// Beat 6%

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
         double[] res = new double[nums.length - k + 1]; 
        for(int i = 0; i + k <= nums.length; i++){
            int[] temp = Arrays.copyOfRange(nums, i, i+k);
            Arrays.sort(temp);
            //System.out.println(Arrays.toString(temp));
            if (k % 2 == 1) res[i] = ((double)temp[k / 2]);
            else res[i] = (((double)temp[k / 2 - 1] + (double)temp[k / 2]) / 2.0);
        }
        return res;
    }
}

// Approach 2 Heaps  Time O(n log k) Sapce O(k + n)-> O(n)

// insertion logK * n

// Beat 96%

class Solution {
    
    // store numbers bigger than and equal to current median
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // store numbers smaller than current median
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
        new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b.compareTo(a); // descending 
            }
        }
    );
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        
        for(int i = 0 ; i <= nums.length; i++){
            if(i >= k){
                res[i - k] = getMedian();  // add median to result 
                remove(nums[i - k]); // remove from left side of window 
            }
            if(i < nums.length){
                add(nums[i]); // add right side of window
            }
        }
        return res;
    }
    // get median from 2 heaps 
    private double getMedian(){
        if(minHeap.isEmpty() && maxHeap.isEmpty()) return 0;
        if(minHeap.size() == maxHeap.size()){ // even number
            return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
        }else return (double)minHeap.peek(); // odd number
    }
    // add num to 2 heaps 
    private void add(int num){
        if (num < getMedian()){
            maxHeap.add(num);
        }else minHeap.add(num);
        // always make size of minHeap = maxHeap (even numbers) 
        // or minHeap - 1 = maxHeap (odd numbers) 
        if(maxHeap.size() > minHeap.size()){
            minHeap.add(maxHeap.poll()); // move smallest ele
        }
        if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.add(minHeap.poll()); // move largest ele
        }
    }
    // remove num from 2 heaps
    private void remove(int num){
        if(num < getMedian()){
            maxHeap.remove(num);
        }else minHeap.remove(num);
        // balance size 
        if(maxHeap.size() > minHeap.size()){
            minHeap.add(maxHeap.poll()); // move smallest ele
        }
        if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.add(minHeap.poll()); // move largest ele
        }
    }
    
}

// Approach TreeMap : faster because TreeMap can remove arbitrary element in logarithmic time.


239. Sliding Window Maximum

// https://leetcode.com/problems/sliding-window-maximum/

/*

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

*/
// Approach heap  Time O(n log k) Space O(k)

 class Solution {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        // base case
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i <= nums.length; i++){ // O(n)
            if(i >= k){
                res[i - k] = heap.peek();
                heap.remove(nums[i - k]);
            }
            if(i < nums.length){
                heap.add(nums[i]); // add right side
            }
        }
        return res;
    }
}

 // Approach ArrayDeque  Time O(n)

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++){
            if(i >= k){
                res[i - k] = nums[q.peek()];
            }
            while(!q.isEmpty() && q.peek() < i - k + 1){
                q.poll(); //remove left side
            }
            while(!q.isEmpty() && nums[q.peekLast()] < nums[i]){
                q.pollLast(); // remove smaller numbers outside of k range
            }
            q.add(i); // add right side
        }
        // set last element
        res[nums.length - k] = nums[q.peek()];
        return res;
    }
}