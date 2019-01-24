900-RLEIterator

// https://leetcode.com/problems/rle-iterator/

/*

Input: ["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
Output: [null,8,8,5,-1]
Explanation: 
RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).
This maps to the sequence [8,8,8,5,5].
RLEIterator.next is then called 4 times:

.next(2) exhausts 2 terms of the sequence, returning 8.  The remaining sequence is now [8, 5, 5].

.next(1) exhausts 1 term of the sequence, returning 8.  The remaining sequence is now [5, 5].

.next(1) exhausts 1 term of the sequence, returning 5.  The remaining sequence is now [5].

.next(2) exhausts 2 terms, returning -1.  This is because the first term exhausted was 5,
but the second term did not exist.  Since the last term exhausted does not exist, we return -1.

*/


class RLEIterator {
    int[] data = null;
    int i = 0;
    int count = 0;
    
    public RLEIterator(int[] A) {
        data = A;   
    }
    
    public int next(int n) {
        while(i < data.length){
            if(count + n > data[i]){
                n -= data[i] - count; // remaining #
                count = 0; // reset count
                i += 2; // next element
            }else{
                count += n;
                return data[i+1];
            }
        }
        return -1;
    }
    
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */