// 191. Number of 1 Bits


// Hamming Weight

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        int mask = 1; // bitwise mask to get 1s
        for (int i = 0; i < 32; i++){
            if (n & mask != 0) res ++;
            mask <<= 1;//bitwise shift
        }
        return res;
        
    }
}


// Hamming Distance
// https://en.wikipedia.org/wiki/Hamming_distance

// 461.  
// https://leetcode.com/problems/hamming-distance/
// = number of positions at which the corresponding bits are different.

class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y, count = 0;
        // count #1's in xor
        for (int i = 0; i < 32; i ++){
            if ((xor & 1) == 1) count++;
            xor >>=1;
        }
        return count;
    }
}


// 190. Reverse Bits


public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i ++){
            result <<= 1;
            result |= (n & 0x1); // bitwise or
            n >>>= 1; // left shift with leftmost bit being 0
        }
        return result;
        
    }
}

