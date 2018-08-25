/*

417. Pacific Atlantic Water Flow
https://leetcode.com/problems/pacific-atlantic-water-flow/description/

Given an m x n matrix of non-negative integers representing the height of each
unit cell in a continent, the "Pacific ocean" touches the left and top edges of
the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to
another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and
Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.

Example:
Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:
[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] 
(positions with parentheses in above matrix).

*/
class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        final int n = matrix.length;
        final int m = matrix[0].length;
        
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        
        // Pacific
        for (int j = 0; j < m; j++) {
            if (!pacific[0][j]) {
                dfs(0, j, matrix, pacific);
            }
        }
        for (int i = 0; i < n; i++) {
            if (!pacific[i][0]) {
                dfs(i, 0, matrix, pacific);
            }
        }
        
        // Atlantic
        for (int j = 0; j < m; j++) {
            if (!atlantic[n-1][j]) {
                dfs(n-1, j, matrix, atlantic);
            }
        }
        for (int i = 0; i < n; i++) {
            if (!atlantic[i][m-1]) {
                dfs(i, m-1, matrix, atlantic);
            }
        }
        
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }
    private void dfs(int i, int j, int[][] matrix, boolean[][] table) {
        table[i][j] = true;
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length &&
                !table[x][y] && matrix[x][y] >= matrix[i][j]) {
                dfs(x, y, matrix, table);
            }
        }
    }
}
// 113 / 113 test cases passed.
// Runtime: 10 ms

