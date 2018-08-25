/*

329. Longest Increasing Path in a Matrix
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is
not allowed).

Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].


Example 2:
Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. 
Moving diagonally is not allowed.

*/


class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        final int n = matrix.length;
        final int m = matrix[0].length;
        
        int[][] result = new int[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (result[i][j] == 0) {
                    max = Math.max(max, dfs(i, j, matrix, result));
                }
            }
        }
        return max;
    }
    private int dfs(int i, int j, int[][] matrix, int[][] result) {
        if (result[i][j] != 0) {
            return result[i][j];
        }
        
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        int length = 0;
        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length &&
               matrix[i][j] < matrix[x][y]) {
                length = Math.max(length, dfs(x, y, matrix, result));
            }
        }
        result[i][j] = length + 1;
        return result[i][j];
    }
}
// 137 / 137 test cases passed.
// Runtime: 11 ms


