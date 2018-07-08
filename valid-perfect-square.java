/*

367. Valid Perfect Square
https://leetcode.com/problems/valid-perfect-square/description/

Given a positive integer num, write a function which returns True if num is a
perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Returns: True

Example 2:
Input: 14
Returns: False

*/

class Solution {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid < num * 1.0 / mid) {
                start = mid + 1;
            } else if (mid > num * 1.0 / mid) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        
        return false;
    }
}
// 67 / 67 test cases passed.
// Runtime: 0 ms

