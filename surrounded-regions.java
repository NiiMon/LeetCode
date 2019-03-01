/*

130. Surrounded Regions
https://leetcode.com/problems/surrounded-regions/

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:
X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:
X X X X
X X X X
X X X X
X O X X

Explanation:
Surrounded regions shouldn’t be on the border, which means that any 'O' on the
border of the board are not flipped to 'X'. Any 'O' that is not on the border
and it is not connected to an 'O' on the border will be flipped to 'X'. Two
cells are connected if they are adjacent cells connected horizontally or
vertically.

*/



class Solution {
    int[] dx = new int[]{0,0,-1,1};
    int[] dy = new int[]{-1,1,0,0};
    int n = 0;
    int m = 0;
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        n = board.length;
        m = board[0].length;

        boolean[][] visited = new boolean[n][m];
        // preserve 'O' areas with 'O' on boarders
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) {
                dfs(board, visited, i, 0, false);
            }
            if (board[i][m - 1] == 'O' && !visited[i][m - 1]) {
                dfs(board, visited, i, m - 1, false);
            }
        }
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O' && !visited[0][j]) {
                dfs(board, visited, 0, j, false);
            }
            if (board[n - 1][j] == 'O' && !visited[n - 1][j]) {
                dfs(board, visited, n - 1, j, false);
            }
        }
        
        // flip 'O' areas with 'O' inside
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    dfs(board, visited, i, j, true);
                }
            }
        }
    }
    private void dfs(char[][] board, boolean[][] visited, int x, int y, 
    				boolean flag) {
        // when flag is true, we flip 'O'; when flag is false, we don't
        visited[x][y] = true;
        if (flag) {
            board[x][y] = 'X';
        }
        for (int k = 0; k < dx.length; k++) {
            int i = x + dx[k];
            int j = y + dy[k];
            if (i >= 0 && i < n && j >= 0 && j < m &&
                !visited[i][j] && board[i][j] == 'O') {
                dfs(board, visited, i, j, flag);
            }
        }
    }
}
// 59 / 59 test cases passed.
// Status: Accepted
// Runtime: 6 ms
// Memory Usage: 49.7 MB


