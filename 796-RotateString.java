796-RotateString

// https://leetcode.com/problems/rotate-string/submissions/


// Brute-force O(N^2), space O(1)


class Solution {
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length())
            return false;
        if (A.length() == 0)
            return true;
        // at each starting point of a, do a search A[s] == B[0]? 
        search: // name the loop
            for (int s = 0; s < A.length(); ++s) {
                for (int i = 0; i < A.length(); ++i) {
                    if (A.charAt((s+i) % A.length()) != B.charAt(i))
                        continue search; 
                }
                return true; // find a rotation
            }
        return false;
    }
}

// Complexity: Time O(n^2), n= length of a ; Space O(n), n = a+a

class Solution {
    public boolean rotateString(String A, String B) {
        // base case if A, B both null or empty, return true
        if (A.length() != B.length()) return false;
        return (A + A).contains(B);
    }
}


// improvement : Time O(n), space O(n)

// KMP (Knuth-Morris-Pratt)  DP

class Solution {
    public boolean rotateString(String A, String B) {
        int N = A.length();
        if (N != B.length()) return false;
        if (N == 0) return true;

        //Compute shift table
        int[] shifts = new int[N+1];
        Arrays.fill(shifts, 1);
        int left = -1;
        for (int right = 0; right < N; ++right) {
            while (left >= 0 && (B.charAt(left) != B.charAt(right)))
                left -= shifts[left];
            shifts[right + 1] = right - left++;
        }

        //Find match of B in A+A
        int matchLen = 0;
        for (char c: (A+A).toCharArray()) {
            while (matchLen >= 0 && B.charAt(matchLen) != c)
                matchLen -= shifts[matchLen];
            if (++matchLen == N) return true;
        }

        return false;
    }
}

