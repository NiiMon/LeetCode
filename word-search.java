/*

79. Word Search
https://leetcode.com/problems/word-search/description/


Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.


*/


class Solution {
    public boolean exist(char[][] board, String word) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) &&
                   dfs(board, i, j, word, 0, new boolean[board.length][board[0].length])) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, int x, int y, String word, int index, boolean[][] visited) {
        // 1. op at node
        visited[x][y] = true;
        
        // 2. op at leaf
        if (index == word.length() - 1) {
            return true;
        }

        // 3. go down to children
        boolean result = false;
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int k = 0; k < dx.length && !result; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && nx < board.length &&
                ny >= 0 && ny < board[0].length &&
                !visited[nx][ny] && board[nx][ny] == word.charAt(index + 1)) {
                result = dfs(board, nx, ny, word, index + 1, visited);
            }
        }

        // 4. go up to parent
        visited[x][y] = false;

        return result;
    }
}
// 87 / 87 test cases passed.
// Status: Accepted
// Runtime: 16 ms
// Memory Usage: 29.8 MB
