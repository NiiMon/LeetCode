/*

695. Max Area of Island
https://leetcode.com/problems/max-area-of-island/description/


Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
(representing land) connected 4-directionally (horizontal or vertical.) You may
assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no
island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island
must be connected 4-directionally.

Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.

*/

class Solution {
    final static int LAND = 1;
    public int maxAreaOfIsland(int[][] grid) {
        final int n = grid.length;
        final int m = grid[0].length;
        if (n == 0 || m == 0) {
            return 0;
        }
        
        int result = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == LAND && !visited[i][j]) {
                    result = Math.max(result, dfs(i, j, grid, visited));
                }
            }
        }
        
        return result;
    }
    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        visited[i][j] = true;
        int area = 1;
        
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length &&
                grid[x][y] == LAND && !visited[x][y]) {
                area += dfs(x, y, grid, visited);
            }
        }
        return area;
    }
}
// 726 / 726 test cases passed.
// Runtime: 34 ms

