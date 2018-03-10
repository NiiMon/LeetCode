/*

204. Count Primes
https://leetcode.com/problems/count-primes/description/


Description:

Count the number of prime numbers less than a non-negative number, n.

*/


class Solution {
    public int countPrimes(int n) {
        int count = 0;
        if (n <= 2) {
            return count;
        }

        boolean[] nums = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!nums[i]) {
                count++;
                if (i <= Math.sqrt(n)) {
                    for (int j = i * 2; j < n; j += i) {
                        nums[j] = true;
                    }
                }
            }
        }

        return count;
    }
}

