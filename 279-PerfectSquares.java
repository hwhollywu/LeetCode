// 279. Perfect Squares

// https://leetcode.com/problems/perfect-squares/description/


// Approach 1 BFS

class Solution {
    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> q = new LinkedList<>();
        boolean[] marked = new boolean[n+1]; //0-n
        q.add(n); // start with n
        marked[n] = true; // mark visited
        int count = 0;
        while(!q.isEmpty()){
            int size = q.size();
            count++;
            while(size-- > 0){
                int cur = q.poll();
                for (int s : squares){
                    //System.out.println(s);
                    int next = cur - s;
                    // base case ignore further s's
                    if (next < 0){
                        break;
                    }
                    // base case: reached, return
                    if (next == 0){
                        return count;
                    }
                    // prevent repeteated adding 
                    if (marked[next]){
                        continue;
                    }
                    // add children
                    marked[next] = true;
                    q.add(next);
                }
            }
        }
        return n; // base case: return 
    }
    
    public List<Integer> generateSquares(int n){
        List<Integer> result = new ArrayList<Integer>();
        int square = 1;
        int diff = 3;
        while (square <= n){
            result.add(square);
            square += diff;
            diff += 2;
        }
        return result;
    }
}


// Approach 2 dp 
// dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1

public int numSquares(int n) {
	int[] dp = new int[n + 1];
	Arrays.fill(dp, Integer.MAX_VALUE);
	dp[0] = 0;
	for(int i = 1; i <= n; ++i) {
		int min = Integer.MAX_VALUE;
		int j = 1;
		while(i - j*j >= 0) {
			min = Math.min(min, dp[i - j*j] + 1);
			++j;
		}
		dp[i] = min;
	}		
	return dp[n];
}


public class Solution {
    public int numSquares(int n) {
        int[] record = new int[n+1];
        for(int i=0;i<=n;i++){
            record[i] = i;
            for(int j=1;j*j<=i;j++){
                record[i] = Math.min(record[i-j*j]+1,record[i]);
            }
        }
        return record[n];
    }
}


class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1]; //0-n
        dp[0] = 0;
        for (int i = 1; i<= n; i++){
            // set min
            dp[i] = i;
            for (int k = 1; k * k <= i; k++){
                dp[i] = Math.min(dp[i-k*k]+1, dp[i]);
            }
        }
        return dp[n];
    }
}