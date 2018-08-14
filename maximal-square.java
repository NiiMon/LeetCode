/*

221. Maximal Square
https://leetcode.com/problems/maximal-square/description/

Given a 2D binary matrix filled with 0's and 1's, find the largest square
containing only 1's and return its area.

Example:
Input: 
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

*/

// dp
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int result = 0;
        int[] pre = new int[m];

        for (int i = 0; i < n; i++) {
            int[] cur = new int[m];
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    cur[j] = 1;
                    if (i > 0 && j > 0) {
                        cur[j] += Math.min(cur[j-1], Math.min(pre[j], pre[j-1]));
                    }
                    result = Math.max(result, cur[j] * cur[j]);
                }
            }
            pre = cur;
        }

        return result;
    }
}
// 68 / 68 test cases passed.
// Runtime: 8 ms

