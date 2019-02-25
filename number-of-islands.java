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


// dfs: dig land to water
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }
    private void dfs(char[][] grid, int x, int y) {
        grid[x][y] = '0';
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int i = x + dx[k];
            int j = y + dy[k];
            if (i >= 0 && i < grid.length &&
                j >= 0 && j < grid[0].length &&
                grid[i][j] == '1') {
                dfs(grid, i, j);
            }
        }
    }
}
// 47 / 47 test cases passed.
// Status: Accepted
// Runtime: 4 ms
// Memory Usage: 41.2 MB

