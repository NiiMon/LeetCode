/*

764. Largest Plus Sign
https://leetcode.com/problems/largest-plus-sign/description/


In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those
cells in the given list mines which are 0. What is the largest axis-aligned plus
sign of 1s contained in the grid? Return the order of the plus sign. If there is
none, return 0.

An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1
along with 4 arms of length k-1 going up, down, left, and right, and made of 1s.
This is demonstrated in the diagrams below. Note that there could be 0s or 1s
beyond the arms of the plus sign, only the relevant area of the plus sign is
checked for 1s.

Examples of Axis-Aligned Plus Signs of Order k:

Order 1:
000
010
000

Order 2:
00000
00100
01110
00100
00000

Order 3:
0000000
0001000
0001000
0111110
0001000
0001000
0000000

Example 1:
Input: N = 5, mines = [[4, 2]]
Output: 2
Explanation:
11111
11111
11111
11111
11011

In the above grid, the largest plus sign can only be order 2.  One of them is
marked in bold.


Example 2:
Input: N = 2, mines = []
Output: 1
Explanation:
There is no plus sign of order 2, but there is of order 1.


Example 3:
Input: N = 1, mines = [[0, 0]]
Output: 0
Explanation:
There is no plus sign, so return 0.

Note:
N will be an integer in the range [1, 500].
mines will have length at most 5000.
mines[i] will be length 2 and consist of integers in the range [0, N-1].
(Additionally, programs submitted in C, C++, or C# will be judged with a
(slightly smaller time limit.)




*/


class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N < 1) {
            return 0;
        }

        Map<Integer, Set<Integer>> zero = new HashMap<>();
        for (int i = 0; i < mines.length; i++) {
            if (!zero.containsKey(mines[i][0])) {
                zero.put(mines[i][0], new HashSet<>());
            }
            zero.get(mines[i][0]).add(mines[i][1]);
        }

        int[][] up = new int[N][N];
        int[][] down = new int[N][N];
        int[][] left = new int[N][N];
        int[][] right = new int[N][N];

        // up
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!(zero.containsKey(i) && zero.get(i).contains(j))) {
                    up[i][j] = 1;
                    if (i - 1 >= 0) {
                        up[i][j] += up[i-1][j];
                    }
                }
            }
        }

        // down
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (!(zero.containsKey(i) && zero.get(i).contains(j))) {
                    down[i][j] = 1;
                    if (i + 1 < N) {
                        down[i][j] += down[i+1][j];
                    }
                }
            }
        }

        // left
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if (!(zero.containsKey(i) && zero.get(i).contains(j))) {
                    left[i][j] = 1;
                    if (j - 1 >= 0) {
                        left[i][j] += left[i][j-1];
                    }
                }
            }
        }

        // right
        for (int j = N - 1; j >= 0; j--) {
            for (int i = 0; i < N; i++) {
                if (!(zero.containsKey(i) && zero.get(i).contains(j))) {
                    right[i][j] = 1;
                    if (j + 1 < N) {
                        right[i][j] += right[i][j+1];
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!(zero.containsKey(i) && zero.get(i).contains(j))) {
                    result = Math.max(result, 
                    	min(up[i][j], down[i][j], left[i][j], right[i][j]));
                }
            }
        }

        return result;
    }
    private int min(int... vals) {
        int min = vals[0];
        for (int val : vals) {
            min = Math.min(min, val);
        }
        return min;
    }
}
// 58 / 58 test cases passed.
// Runtime: 714 ms

