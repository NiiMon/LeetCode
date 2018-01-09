/*

85. Maximal Rectangle
https://leetcode.com/problems/maximal-rectangle/description/


Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

*/


class Solution {
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        
        if (matrix.length == 0 || matrix[0].length == 0) {
            return max;
        }
        
        int[] row = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int[] newRow = new int[matrix[0].length];
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '0') {
                    newRow[j] = matrix[i][j] - '0' + row[j];
                }
            }
            row = newRow;
            max = Math.max(max, largestRectangleArea(row));
        }
        
        return max;
    }
    public int largestRectangleArea(int[] h) {
        // left edge
        int[] left = new int[h.length];
        for (int i = 0; i < h.length; i++) {
            int lEdge = i;
            while (lEdge - 1 >= 0 && h[lEdge - 1] >= h[i]) {
                lEdge = left[lEdge - 1];
            }
            left[i] = lEdge;
        }
        
        // right edge
        int[] right = new int[h.length];
        for (int i = h.length - 1; i >= 0; i--) {
            int rEdge = i;
            while (rEdge + 1 <= h.length - 1 && h[rEdge + 1] >= h[i]) {
                rEdge = right[rEdge + 1];
            }
            right[i] = rEdge;
        }
        
        // calculate max area
        int max = 0;
        for (int i = 0; i < h.length; i++) {
            max = Math.max(max, h[i] * (right[i] - left[i] + 1));
        }
        
        return max;
    }
}

