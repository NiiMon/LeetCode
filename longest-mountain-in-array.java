/*

845. Longest Mountain in Array
https://leetcode.com/problems/longest-mountain-in-array/description/


Let's call any (contiguous) subarray B (of A) a mountain if the following 
properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i]
 > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.

Example 1:
Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

Example 2:
Input: [2,2,2]
Output: 0
Explanation: There is no mountain.


Note:
0 <= A.length <= 10000
0 <= A[i] <= 10000

Follow up:
Can you solve it using only one pass?
Can you solve it in O(1) space?

*/

// version 1
// Time: O(n)
// Space: O(n)
class Solution {
    public int longestMountain(int[] a) {
        int[] left = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            left[i] = i;
            if (i >= 1 && a[i] > a[i - 1]) {
                left[i] = left[i - 1];
            }
        }

        int[] right = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            right[i] = i;
            if (i + 1 < a.length && a[i] > a[i + 1]) {
                right[i] = right[i + 1];
            }
        }

        int result = 0;
        for (int i = 0; i < a.length; i++) {
            int len = right[i] - left[i] + 1;
            len = left[i] < i && i < right[i] ? len : 0;
            result = Math.max(result, len);
        }

        return result;
    }
}
// 72 / 72 test cases passed.
// Runtime: 8 ms


// version 2
// Time: O(n)
// Space: O(1)
class Solution {
    public int longestMountain(int[] a) {
        int result = 0;
        int n = a.length;
        int i = 0;
        while (i < n - 1) {
            // find start
            for (; i + 1 < n && a[i] >= a[i+1]; i++);
            int start = i;

            // find peak
            for (; i + 1 < n && a[i] < a[i+1]; i++);
            int peak = i;
            
            // find end
            for (; i + 1 < n && a[i] > a[i+1]; i++);

            // result
            if (i > peak) {
                result = Math.max(result, i - start + 1);
            }
        }
        
        return result;
    }
}
// 72 / 72 test cases passed.
// Runtime: 6 ms


