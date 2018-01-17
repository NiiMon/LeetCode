/*

172. Factorial Trailing Zeroes
https://leetcode.com/problems/factorial-trailing-zeroes/description/


Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.

*/


class Solution {
    public int trailingZeroes(int n) {
        int result = 0;
        for (int i = n; i > 0; i /= 5) {
            result += i / 5;
        }
        return result;
    }
}

