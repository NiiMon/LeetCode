/*

283. Move Zeroes
https://leetcode.com/problems/move-zeroes/description/


Given an array nums, write a function to move all 0's to the end of it while
maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
should be [1, 3, 12, 0, 0].

Note: You must do this in-place without making a copy of the array. Minimize
the total number of operations.

*/

class Solution {
    public void moveZeroes(int[] a) {
        int last = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                a[++last] = a[i];
            }
        }
        for(int i = ++last; i < a.length; i++) {
            a[i] = 0;
        }
    }
}

