45.55-JumpGame

// https://leetcode.com/problems/jump-game/

/*

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
*/

// Complexity: Time O(n), Space O(1) 

// Approach Greedy: from the start, select the local optimum to reach the global optimum

public boolean canJump(int[] nums) {
    int max = 0;
    for(int i=0;i<nums.length;i++){
        if(i>max) {return false;} // max can't reach last index
        max = Math.max(nums[i]+i,max);
    }
    return true;
}


// Approach DP: from end to head

public boolean canJump(int[] nums) {
	int n = nums.length;
	boolean[] jump = boolean[n];
	jump[n - 1] = true;
	for(int i = n-2; i >= 0; i--){
		// for each nums[i]
		for(int j = 0; j <= nums[i] && i+j<n; j++){
			if (jump[i+j] == true){
				jump[i] = true;
				break;
			}
		}
	}
	return jump[0];
}



// https://leetcode.com/problems/jump-game-ii/

/*

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

*/

// Approach Greedy O(n)

public int jump(int[] nums) {
	int jumps = 0;
	int curEnd = 0; 
	int max = 0; 
	for(int i = 0; i < nums.length - 1; i++){
		max = Math.max(max, i + nums[i]);
		// reach end, start another jump
		if (i == curEnd){
			jumps++;
			curEnd = max;
		}

	}
	return jumps;
}

// Approach BFS

public int jump(int[] nums) {
	int n = nums.length;
	if (n < 2) return 0; // base 
	Map<Integer, Integer> map = new HashMap(); // index - jumps
	Queue<Integer> q = new LinkedList();
	q.add(0);
	map.put(0,0);
	while(q.size() != 0){
		int index = q.remove();
		int jumps = map.get(index);
		for(int i = nums[index]; i > 0; i--){
			int next = index + i;
			if (next >= n - 1) return jumps + 1;
			if (!map.containsKey(next)){
				map.put(next, jumps + 1);
				q.add(next);
			}
		}
	}
	return -1;
}


