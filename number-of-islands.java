/*

200. Number of Islands
https://leetcode.com/problems/number-of-islands/description/

Given a 2d grid map of '1's (land) and '0's (water), count the number of
islands. An island is surrounded by water and is formed by connecting adjacent
lands horizontally or vertically. You may assume all four edges of the grid are
all surrounded by water.

Example 1:
Input:
11110
11010
11000
00000
Output: 1

Example 2:
Input:
11000
11000
00100
00011
Output: 3

*/

class Solution {
    final static char WATER = '0';
    final static char LAND = '1';
    public int numIslands(char[][] grid) {
        final int n = grid.length;
        final int m = grid[0].length;
        if (n == 0 || m == 0) {
            return 0;
        }
        
        boolean[][] visited = new boolean[n][m];
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == LAND && !visited[i][j]) {
                    result++;
                    dfs(i, j, grid, visited);
                }
            }
        }
        return result;
    }
    private void dfs(int i, int j, char[][] grid, boolean[][] visited) {
        visited[i][j] = true;
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length &&
                grid[x][y] == LAND && !visited[x][y]) {
                dfs(x, y, grid, visited);
            }
        }
    }
}
// 47 / 47 test cases passed.
// Runtime: 9 ms

