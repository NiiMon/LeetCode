/*

64. Minimum Path Sum
https://leetcode.com/problems/minimum-path-sum/description/

Given a m x n grid filled with non-negative numbers, find a path from top left
to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

*/

class Solution {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;

        int[] cur = new int[m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) {
                    cur[j] = grid[i][j];
                } else {
                    cur[j] = Math.min(j + 1 < m ?
                                      cur[j + 1] : Integer.MAX_VALUE,
                                      i + 1 < n ?
                                      cur[j] : Integer.MAX_VALUE)
                        + grid[i][j];
                }
            }
        }

        return cur[0];
    }
}
// 61 / 61 test cases passed.
// Runtime: 5 ms

