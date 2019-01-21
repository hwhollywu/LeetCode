89. Gray Code

// https://leetcode.com/problems/gray-code/

/*
Input: 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2

For a given n, a gray code sequence may not be uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence.

00 - 0
10 - 2
11 - 3
01 - 1
*/

/*
Input: 0
Output: [0]
Explanation: We define the gray code sequence to begin with 0.
             A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
             Therefore, for n = 0 the gray code sequence is [0].
*/

// use of bitwise operators ^ = XOR


class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for(int i =  0; i < Math.pow(2,n); i++){
            result.add(i ^ i/2); // ??
        }
        return result;
    }
}

// 00,01,11,10 -> (000,001,011,010 ) (110,111,101,100). 
// The middle two numbers only differ at their highest bit, 
// while the rest numbers of part two are exactly symmetric of part one. 

public List<Integer> grayCode(int n) {
    List<Integer> rs=new ArrayList<Integer>();
    rs.add(0);
    for(int i=0;i<n;i++){
        int size=rs.size();
        for(int k=size-1;k>=0;k--)
            rs.add(rs.get(k) | 1<<i);
    }
    return rs;
}