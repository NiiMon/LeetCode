/*

827. Making A Large Island
https://leetcode.com/problems/making-a-large-island/description/


In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally
connected group of 1s).

Example 1:
Input: [[1, 0], [0, 1]]
Output: 3
Explanation: 
Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:
Input: [[1, 1], [1, 0]]
Output: 4
Explanation: 
Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:
Input: [[1, 1], [1, 1]]
Output: 4
Explanation: 
Can't change any 0 to 1, only one island with area = 4.

*/

class Solution {
    final static int LAND = 1;
    final static int WATER = 0;
    public int largestIsland(int[][] grid) {
        final int n = grid.length;
        final int m = grid[0].length;
        if (n == 0 || m == 0) {
            return 0;
        }
        
        int id = 1;
        Map<Integer, Integer> idToArea = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == LAND) {
                    int area = dfs(i, j, ++id, grid);
                    idToArea.put(id, area);
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == WATER) {
                    Set<Integer> ids = new HashSet<>();
                    if (i - 1 >= 0 && grid[i-1][j] != WATER) {
                        ids.add(grid[i-1][j]);
                    }
                    if (i + 1 < n && grid[i+1][j] != WATER) {
                        ids.add(grid[i+1][j]);
                    }
                    if (j - 1 >= 0 && grid[i][j-1] != WATER) {
                        ids.add(grid[i][j-1]);
                    }
                    if (j + 1 < m && grid[i][j+1] != WATER) {
                        ids.add(grid[i][j+1]);
                    }
                    int sum = 1;
                    for (int ID : ids) {
                        sum += idToArea.get(ID);
                    }
                    result = Math.max(result, sum);
                }
            }
        }
        
        return result == 0 ? n * m : result;
    }
    private int dfs(int i, int j, int id, int[][] grid) {
        grid[i][j] = id;
        int area = 1;
        
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length &&
                grid[x][y] == LAND) {
                area += dfs(x, y, id, grid);
            }
        }
        return area;
    }
}
// 63 / 63 test cases passed.
// Runtime: 21 ms

