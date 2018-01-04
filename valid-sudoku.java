/*

36. Valid Sudoku
https://leetcode.com/problems/valid-sudoku/description/


Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with
the character '.'.


A partially filled sudoku which is valid.

Note: A valid Sudoku board (partially filled) is not necessarily solvable.
Only the filled cells need to be validated.

*/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] nums = new boolean[9];
        
        // horizontal
        for (int i = 0; i < board.length; i++) {
            nums = new boolean[9];
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.' && !helper(board[i][j], nums)) {
                    return false;
                }
            }
        }
        
        // vertical
        for (int j = 0; j < board[0].length; j++) {
            nums = new boolean[9];
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != '.' && !helper(board[i][j], nums)) {
                    return false;
                }
            }
        }
        
        // 3 x 3
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[0].length; j += 3) {
                nums = new boolean[9];
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        if (board[i + x][j + y] != '.' &&
                            !helper(board[i + x][j + y], nums)) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    private boolean helper(char c, boolean[] nums) {
        if (nums[c - '1']) {
            return false;
        }
        nums[c - '1'] = true;
        return true;
    }
}
