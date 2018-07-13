/*

209. Minimum Size Subarray Sum
https://leetcode.com/problems/minimum-size-subarray-sum/description/


Given an array of n positive integers and a positive integer s, find the minimal
length of a contiguous subarray of which the sum ≥ s. If there isn't one, return
0 instead.

Example: 
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem
constraint.

Follow up:
If you have figured out the O(n) solution, try coding another solution of which
the time complexity is O(n log n).

*/



class Solution {
    public int minSubArrayLen(int s, int[] a) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;

        for (int i = 0; i < a.length; i++) {
            sum += a[i];

            while (sum >= s) {
                result = Math.min(result, i - start + 1);
                sum -= a[start];
                start++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
// 15 / 15 test cases passed.
// Runtime: 2 ms


