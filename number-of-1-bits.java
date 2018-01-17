/*

191. Number of 1 Bits
https://leetcode.com/problems/number-of-1-bits/description/


Write a function that takes an unsigned integer and returns the number of ’1'
bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation
00000000000000000000000000001011, so the function should return 3.

*/


public class Solution {
    // you need to treat n as an unsigned value 
    // unsigned 10000000000000000000000000000000 is positive 
    // while signed 10000000000000000000000000000000 is negative
    public int hammingWeight(int n) {
        int result = 0;
        for (int i = n; i != 0; i &= i - 1) {
            result++;
        }
        return result;
    }
}

