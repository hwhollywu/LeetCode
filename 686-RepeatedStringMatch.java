686-RepeatedStringMatch

// https://leetcode.com/problems/repeated-string-match/


// complexity:  Time O(N∗(N+M)) N=B's length, M = A's length
//  Space o(M+N) 

class Solution {
    public int repeatedStringMatch(String A, String B) {
        // base cases
        int res = 1;
        if (A == null || A.length() == 0) return -1;
        if (B == null || B.length() == 0) return res;
        StringBuilder sb = new StringBuilder(A);
        while (sb.length() < B.length()){
            sb.append(A);
            res++;
        }
        if (sb.indexOf(B) != -1) return res;
        else {
            // append one more time, make sure sb's length - A's length > B's length
            sb.append(A);
            res++;
        }
        return (sb.indexOf(B) != -1) ? res : -1;
    }
}


// Approach 2 : Rolling Hash

// Time : O(m+n), space O(1)

import java.math.BigInteger;

class Solution {
    public boolean check(int index, String A, String B) {
        for (int i = 0; i < B.length(); i++) {
            if (A.charAt((i + index) % A.length()) != B.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public int repeatedStringMatch(String A, String B) {
        int q = (B.length() - 1) / A.length() + 1;
        int p = 113, MOD = 1_000_000_007;
        int pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(MOD)).intValue();

        long bHash = 0, power = 1;
        for (int i = 0; i < B.length(); i++) {
            bHash += power * B.codePointAt(i);
            bHash %= MOD;
            power = (power * p) % MOD;
        }

        long aHash = 0; power = 1;
        for (int i = 0; i < B.length(); i++) {
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;
            power = (power * p) % MOD;
        }

        if (aHash == bHash && check(0, A, B)) return q;
        power = (power * pInv) % MOD;

        for (int i = B.length(); i < (q + 1) * A.length(); i++) {
            aHash -= A.codePointAt((i - B.length()) % A.length());
            aHash *= pInv;
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;
            if (aHash == bHash && check(i - B.length() + 1, A, B)) {
                return i < q * A.length() ? q : q + 1;
            }
        }
        return -1;
    }
}


