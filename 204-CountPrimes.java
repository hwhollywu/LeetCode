// https://leetcode.com/problems/count-primes/description/

class Solution {
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n]; // store numbers that are not prime
        int count = 0; //result
        for (int i = 2; i < n; i++){
            // skip if not prime
            if (notPrimes[i]) continue; 
            count++;
            //set notPrimes, 2's multiples, 3's multiples..
            for (int j = 2; i*j < n ; j++){
                notPrimes[i*j] = true;
            }
        }
        return count;
    }
}

