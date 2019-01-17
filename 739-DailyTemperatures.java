729-DailyTemperatures

// https://leetcode.com/problems/daily-temperatures/


// Brute Force O(n)
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        for (int i = 0; i < T.length - 1; i++){
            int cur = T[i];
            for (int j = i + 1; j < T.length; j++){
                int comp = T[j];
                if (comp > cur){
                    // System.out.println("i: " + i + "cur: "+ cur +"j: "+ j +"comp:" + comp);
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
        
        
    }
}


// Approach Array :  Time O(NW) N =length of T; W=the number of allowed values for T[i]
// Space O(N+W) ans + next
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        // next array: T[i] = 50, 
        // we only need to check for the next occurrence of 51, 52, ..., 100
        // and take the one that occurs soonest.
        // index = temp, value = T index
        // next[73] = 7   
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; --i) { // from the end 
        	// the first occurrence of a warmer value
        	// the minimum of next[T[i]+1], next[T[i]+2], ..., next[100].
            int warmer_index = Integer.MAX_VALUE; 
            for (int t = T[i] + 1; t <= 100; ++t) {
            	// t = temp value occurred later
                if (next[t] < warmer_index) // update warmer index 
                    warmer_index = next[t];
            }
            if (warmer_index < Integer.MAX_VALUE)
                ans[i] = warmer_index - i; // store temperature difference in ans
            next[T[i]] = i;
        }
        return ans;
    }
}


// Approach Stack:  Time Complexity: O(N) Space O(W)

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        // stores index of T, only index of ascending temperatures 
        for (int i = T.length - 1; i >= 0; --i) {
        	// if greater temp is found, pop & replace max at peek repeatly 
        	// >= BECAUSE if same value, not warmer, need to pop
        	// e.g. [89,62,70,58,(47,47),46,76,100,70 ] -> [8,1,5,4,(3,2),1,1,0,0]
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            // calc ans value, then push
            stack.push(i);
        }
        return ans;
    }
}